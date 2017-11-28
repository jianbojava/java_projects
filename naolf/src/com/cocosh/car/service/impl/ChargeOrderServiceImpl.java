package com.cocosh.car.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cocosh.framework.annotation.LogClass;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.AESUtil;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.FirmMapper;
import com.cocosh.member.mapper.MemberMapper;
import com.cocosh.member.model.Firm;
import com.cocosh.member.model.Member;
import com.cocosh.car.mapper.ChargeOrderMapper;
import com.cocosh.car.mapper.OrderLogMapper;
import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.model.ChargeOrderDetail;
import com.cocosh.car.model.ChargeOrderExport;
import com.cocosh.car.model.OrderLog;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.car.service.ChargeOrderService;

@Transactional
@Service
public class ChargeOrderServiceImpl implements ChargeOrderService {
	@Autowired
	private ChargeOrderMapper mapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FirmMapper firmMapper;
	@Autowired
	private PaymentLogMapper payLogMapper;
	@Autowired
	private OrderLogMapper logMapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;

	@LogClass(module = "充电订单管理", method = "添加")
	@Override
	public boolean add(ChargeOrder po) {
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "充电订单管理", method = "删除")
	@Override
	public boolean del(String ids) {
//		return mapper.del(ids.split(",")) > 0;
    	return true;
	}

	@LogClass(module = "充电订单管理", method = "修改")
	@Override
	public boolean update(ChargeOrder po) {
		return mapper.update(po) > 0;
	}
	
	@Override
	public void notifyUpdate(JSONObject dataJsonObject) {
		String orderSn=dataJsonObject.getString("StartChargeSeq");
		double totalMoney=dataJsonObject.getDouble("TotalMoney");
		//更新主表数据
		ChargeOrder upd=new ChargeOrder();
		upd.setSn(orderSn);
		upd.setConnectorID(dataJsonObject.getString("ConnectorID"));
		upd.setStartTime(dataJsonObject.getDate("StartTime"));
		upd.setEndTime(dataJsonObject.getDate("EndTime"));
		upd.setTotalPower(dataJsonObject.getDouble("TotalPower"));
		upd.setTotalElecMoney(dataJsonObject.getDouble("TotalElecMoney"));
		upd.setTotalSeviceMoney(dataJsonObject.getDouble("TotalSeviceMoney"));
		upd.setTotalMoney(totalMoney);
		upd.setStopReason(dataJsonObject.getInteger("StopReason"));
		upd.setSumPeriod(dataJsonObject.getInteger("SumPeriod"));
		upd.setStatus(1);//充电完成
		mapper.update(upd);
		
		//删除详情记录
		mapper.delOrderDetail(orderSn);
		JSONArray detailArray=dataJsonObject.getJSONArray("ChargeDetails");
		if (detailArray!=null) {
			//添加详细记录
			for (int i = 0; i < detailArray.size(); i++) {
				JSONObject detailObj=detailArray.getJSONObject(i);
				ChargeOrderDetail orderDetail=new ChargeOrderDetail();
				orderDetail.setDetailStartTime(detailObj.getDate("DetailStartTime"));
				orderDetail.setDetailEndTime(detailObj.getDate("DetailEndTime"));
				orderDetail.setElecPrice(detailObj.getDouble("ElecPrice"));
				orderDetail.setSevicePrice(detailObj.getDouble("SevicePrice"));
				orderDetail.setDetailPower(detailObj.getDouble("DetailPower"));
				orderDetail.setDetailElecMoney(detailObj.getDouble("DetailElecMoney"));
				orderDetail.setDetailSeviceMoney(detailObj.getDouble("DetailSeviceMoney"));
				orderDetail.setOrderSn(orderSn);
				mapper.addOrderDetail(orderDetail);
			}
		}
		//扣款
		Member logM=memberMapper.queryById(mapper.queryById(orderSn).getMem_id());
		//普通用户扣除账户余额
		if (logM.getType()==0) {
//			if (logM.getAccount()>totalMoney) {
//				logM.setAccount(logM.getAccount()-totalMoney);
//				memberMapper.updateAccount(logM);
//				//更新订单状态
//				ChargeOrder o=new ChargeOrder();
//				o.setSn(orderSn);
//				o.setPay_amount(totalMoney);
//				o.setPay_status(1);
//				mapper.update(o);
//				//添加支付流水记录
//				payLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),totalMoney,"订单支付",orderSn,null, 0, 1, 0,null));
//			}
		}else {
			Firm firm=firmMapper.queryById(logM.getFirm_id());
			if (firm.getLeft_amount()>totalMoney) {
				firm.setCredit_amount(firm.getCredit_amount()+totalMoney);
				firm.setUsed_amount(firm.getUsed_amount()+totalMoney);
				firm.setLeft_amount(firm.getLeft_amount()-totalMoney);
				firmMapper.updateAccount(firm);
				//更新订单状态
				ChargeOrder o=new ChargeOrder();
				o.setSn(orderSn);
				o.setPay_amount(totalMoney);
				o.setPay_status(1);
				mapper.update(o);
				//添加支付流水记录
				payLogMapper.add(new PaymentLog(logM.getId(), logM.getFirm_id(),totalMoney,"订单支付",orderSn+"【充电】",null, 0, 1, 0,null));
			}
		}
		//添加日志
		logMapper.add(new OrderLog(StringUtil.getUuid(),orderSn,null,null,"订单推送"));
	}

	@Override
	public Page<ChargeOrder> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<ChargeOrder> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public ChargeOrder queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public ChargeOrder queryValid(String mem_id) {
		return mapper.queryValid(mem_id);
	}

	@Override
	public List<Firm> queryFirmFromOrder() {
		return mapper.queryFirmFromOrder();
	}

	@Override
	public Integer queryNoReadCount() {
		return mapper.queryNoReadCount();
	}

	@Override
	public List<ChargeOrderDetail> queryOrderDetail(String sn) {
		return mapper.queryOrderDetail(sn);
	}

	@Override
	public boolean updateIsRead(String sn) {
		return mapper.updateIsRead(sn)>0;
	}

	@LogClass(module = "充电订单管理", method = "对账")
	@Override
	public boolean checkAmount(ChargeOrder po) {
		po.setSns(po.getSn().split(","));
		po.setStatus(2);//对账
		return mapper.updateM(po)>0;
	}

	@Override
	public List<ChargeOrderExport> queryExport(BaseConditionVO vo) {
		return mapper.queryExport(vo);
	}

	@Override
	public List<ChargeOrder> queryPageByStatus(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<ChargeOrder> page=PaginationInterceptor.endPage();
		//app端订单状态处理
		List<ChargeOrder> list=page.getResult();
		for (ChargeOrder o:list) {
			if (o.getStatus()>=1) {
				o.setOrder_status("已完成");
			}
		}
		return list;
	}

	@Override
	public void orderNotify(String sn, String trade_no,int pay_type) {
		if (paymentLogMapper.queryPayValid(trade_no)==0) {
			ChargeOrder order=mapper.queryById(sn);
			//修改订单信息
			ChargeOrder upd=new ChargeOrder();
			upd.setSn(sn);
			upd.setPay_amount(order.getTotalMoney());
			upd.setPay_status(1);
			mapper.update(upd);
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(order.getMem_id(), order.getFirm_id(),order.getTotalMoney(),"订单支付",sn+"【充电】",trade_no, pay_type, 1, 0,null));
		}
	}

	@Override
	public AjaxResult accountPay(BaseConditionVO vo) {
		Map<String, Object> dataMap=new HashMap<String, Object>();
		Member logM=memberMapper.queryByCredential(vo.getCredential());
		if (logM!=null) {
			//获取支付订单
			ChargeOrder order=mapper.queryById(vo.getId());
			double totalAmount=order.getTotalMoney();
			if (order.getFirm_id()==null) {
				if (!vo.getPay_pwd().equals(logM.getPay_pwd())) {
					return new AjaxResult(CommonUtil.ERROR_CODE,"支付密码错误",dataMap);
				}
				if (totalAmount>logM.getAccount()) {
					return new AjaxResult("2","账户余额不足",dataMap);
				}
				//扣除账户余额
				logM.setAccount(logM.getAccount()-totalAmount);
				memberMapper.updateAccount(logM);
			}else{
				Firm firm=firmMapper.queryById(order.getFirm_id());
				if (totalAmount>firm.getLeft_amount()) {
					return new AjaxResult(CommonUtil.ERROR_CODE,firm.getPay_type()==0?"企业信用额度不足":"企业账户余额不足",dataMap);
				}
				firm.setCredit_amount(firm.getCredit_amount()+totalAmount);
				firm.setUsed_amount(firm.getUsed_amount()+totalAmount);
				firm.setLeft_amount(firm.getLeft_amount()-totalAmount);
				firmMapper.updateAccount(firm);
			}
			//修改订单状态
			ChargeOrder upd=new ChargeOrder();
			upd.setSn(vo.getId());
			upd.setPay_amount(totalAmount);
			upd.setPay_status(1);
			mapper.update(upd);
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(order.getMem_id(), order.getFirm_id(),totalAmount,"订单支付",order.getSn()+"【充电】",null, 0, 1, 0,null));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
	}

}
