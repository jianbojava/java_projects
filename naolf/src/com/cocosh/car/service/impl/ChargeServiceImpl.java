package com.cocosh.car.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.FirmMapper;
import com.cocosh.member.model.Firm;
import com.cocosh.member.model.Member;
import com.cocosh.car.mapper.ChargeMapper;
import com.cocosh.car.mapper.ChargeOrderMapper;
import com.cocosh.car.mapper.ChargeTokenMapper;
import com.cocosh.car.mapper.ConnectorInfoMapper;
import com.cocosh.car.mapper.EquipmentInfoMapper;
import com.cocosh.car.mapper.OrderLogMapper;
import com.cocosh.car.mapper.StationInfoMapper;
import com.cocosh.car.model.Charge;
import com.cocosh.car.model.ChargeOrder;
import com.cocosh.car.model.ConnectorInfo;
import com.cocosh.car.model.EquipmentInfo;
import com.cocosh.car.model.OrderLog;
import com.cocosh.car.model.StationInfo;
import com.cocosh.car.service.ChargeService;

@Transactional
@Service
public class ChargeServiceImpl implements ChargeService {
	@Autowired
	private ChargeMapper mapper;
	@Autowired
	private ChargeTokenMapper tokenMapper;
	@Autowired
	private StationInfoMapper stationMapper;
	@Autowired
	private EquipmentInfoMapper equipmentMapper;
	@Autowired
	private ConnectorInfoMapper connectMapper;
	@Autowired
	private ChargeOrderMapper chargeOrderMapper;
	@Autowired
	private FirmMapper firmMapper;
	@Autowired
	private OrderLogMapper logMapper;
	
	@LogClass(module = "充电桩管理", method = "添加")
	@Override
	public boolean add(Charge po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "充电桩管理", method = "删除")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "充电桩管理", method = "修改")
	@Override
	public boolean update(Charge po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Charge> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Charge> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Charge queryById(String id) {
		return mapper.queryById(id);
	}
	
	
	/*******************特来电对接*******************/

	@Override
	public String query_token() {
		String token=tokenMapper.queryLast();
		if (token==null) {
			token=update_token();
		}
		return token;
	}
	
	@Override
	public String update_token() {
		JSONObject paramJson=new JSONObject();
		paramJson.put("OperatorID",CommonUtil.OperatorID);
		paramJson.put("OperatorSecret",CommonUtil.OperatorSecret);
		JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_token, null, paramJson.toString());
		if (retJson!=null) {
			JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
			String token=dataJson.getString("AccessToken");
	    	tokenMapper.add(token);
	    	return token;
		}
		return null;
	}

	@Override
	public AjaxResult query_stations_info() {
		AjaxResult result=new AjaxResult("0","更新成功");
		for(int n=1;;n++ ){
			JSONObject paramJson=new JSONObject();
			paramJson.put("LastQueryTime","");
			paramJson.put("PageNo",n);
			paramJson.put("PageSize",100);
			JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_stations_info, query_token(), paramJson.toString());
			if (retJson.getInteger("Ret")==4002) {
				retJson=HttpUtil.chargePost(CommonUtil.query_stations_info, update_token(), paramJson.toString());
			}
			if (retJson.getInteger("Ret")!=0) {
				result=new AjaxResult(retJson.getString("Ret"),retJson.getString("Msg"));
				break;
			}
			JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
			JSONArray stationArray=dataJson.getJSONArray("StationInfos");
			if (stationArray.size()==0) {
				break;
			}
			ArrayList<StationInfo> stationList=new ArrayList<StationInfo>();
			ArrayList<EquipmentInfo> equipmentList=new ArrayList<EquipmentInfo>();
			ArrayList<ConnectorInfo> connectList=new ArrayList<ConnectorInfo>();
			for (int i=0;i<stationArray.size();i++) {
				//添加station
				JSONObject stationObject=stationArray.getJSONObject(i);
				StationInfo station=new StationInfo();
				station.setStationID(stationObject.getString("StationID"));
				station.setOperatorID(stationObject.getString("OperatorID"));
				station.setEquipmentOwnerID(stationObject.getString("EquipmentOwnerID"));
				station.setStationName(stationObject.getString("StationName"));
				station.setCountryCode(stationObject.getString("CountryCode"));
				station.setAreaCode(stationObject.getString("AreaCode"));
				station.setAddress(stationObject.getString("Address"));
				station.setStationTel(stationObject.getString("StationTel"));
				station.setServiceTel(stationObject.getString("ServiceTel"));
				station.setStationType(stationObject.getInteger("StationType"));
				station.setStationStatus(stationObject.getInteger("StationStatus"));
				station.setParkNums(stationObject.getInteger("ParkNums"));
				station.setStationLng(stationObject.getDouble("StationLng"));
				station.setStationLat(stationObject.getDouble("StationLat"));
				station.setSiteGuide(stationObject.getString("SiteGuide"));
				station.setConstruction(stationObject.getInteger("Construction"));
				String pictures="";
				JSONArray picArray=stationObject.getJSONArray("Pictures");
				for(int p=0;p<picArray.size();p++){
					pictures+=","+picArray.getString(p);
				}
				if(pictures.contains(","))station.setPictures(pictures.substring(1));
				station.setMatchCars(stationObject.getString("MatchCars"));
				station.setParkInfo(stationObject.getString("ParkInfo"));
				station.setBusineHours(stationObject.getString("BusineHours"));
				station.setElectricityFee(stationObject.getString("ElectricityFee"));
				station.setServiceFee(stationObject.getString("ServiceFee"));
				station.setParkFee(stationObject.getString("ParkFee"));
				station.setPayment(stationObject.getString("Payment"));
				station.setSupportOrder(stationObject.getInteger("SupportOrder"));
				station.setRemark(stationObject.getString("Remark"));
				station.setType(1);//特来电充电桩类型
				stationList.add(station);
				//添加equipment
				JSONArray equipmentArray=stationObject.getJSONArray("EquipmentInfos");
				for (int j = 0; j < equipmentArray.size(); j++) {
					JSONObject equipmentObject=equipmentArray.getJSONObject(j);
					EquipmentInfo equipmentInfo=new EquipmentInfo();
					equipmentInfo.setEquipmentID(equipmentObject.getString("EquipmentID"));
					equipmentInfo.setManufacturerID(equipmentObject.getString("ManufacturerID"));
					equipmentInfo.setEquipmentModel(equipmentObject.getString("EquipmentModel"));
					equipmentInfo.setProductionDate(equipmentObject.getString("ProductionDate"));
					equipmentInfo.setEquipmentType(equipmentObject.getInteger("EquipmentType"));
					equipmentInfo.setEquipmentLng(equipmentObject.getDouble("EquipmentLng"));
					equipmentInfo.setEquipmentLat(equipmentObject.getDouble("EquipmentLat"));
					equipmentInfo.setStationID(stationObject.getString("StationID"));
					equipmentList.add(equipmentInfo);
					//添加connect
					JSONArray connectArray=equipmentObject.getJSONArray("ConnectorInfos");
					for (int k = 0; k < connectArray.size(); k++) {
						JSONObject connectObject=connectArray.getJSONObject(k);
						ConnectorInfo connectorInfo=new ConnectorInfo();
						connectorInfo.setConnectorID(connectObject.getString("ConnectorID"));
						connectorInfo.setConnectorName(connectObject.getString("ConnectorName"));
						connectorInfo.setConnectorType(connectObject.getInteger("ConnectorType"));
						connectorInfo.setVoltageUpperLimits(connectObject.getInteger("VoltageUpperLimits"));
						connectorInfo.setVoltageLowerLimits(connectObject.getInteger("VoltageLowerLimits"));
						connectorInfo.setCurrent(connectObject.getInteger("Current"));
						connectorInfo.setPower(connectObject.getDouble("Power"));
						connectorInfo.setParkNo(connectObject.getString("ParkNo"));
						connectorInfo.setStationID(stationObject.getString("StationID"));
						connectorInfo.setEquipmentID(equipmentObject.getString("EquipmentID"));
						connectList.add(connectorInfo);
					}
				}
			}
			if(!stationList.isEmpty())stationMapper.add(stationList);
			if(!equipmentList.isEmpty())equipmentMapper.add(equipmentList);
			if(!connectList.isEmpty())connectMapper.add(connectList);
		}
		return result;
	}

	@Override
	public AjaxResult query_station_status() {
		JSONObject paramJson=new JSONObject();
		paramJson.put("StationIDs",new String[]{"3501010001"});
		JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_station_status, query_token(), paramJson.toString());
		if (retJson.getInteger("Ret")==4002) {
			retJson=HttpUtil.chargePost(CommonUtil.query_station_status, update_token(), paramJson.toString());
		}
		JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		
		System.out.println();
		System.out.println(dataJson.toString());
		System.out.println();
		
		return new AjaxResult("0","",dataJson.toString());
	}
	
	@Override
	public AjaxResult query_station_stats() {
		JSONObject paramJson=new JSONObject();
		paramJson.put("StationID","3501010001");
		paramJson.put("StartTime","2015-01-01");
		paramJson.put("EndTime","2016-12-12");
		JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_station_stats, query_token(), paramJson.toString());
		if (retJson.getInteger("Ret")==4002) {
			retJson=HttpUtil.chargePost(CommonUtil.query_station_stats, update_token(), paramJson.toString());
		}
		JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		
		System.out.println();
		System.out.println(dataJson.toString());
		System.out.println();
		
		return new AjaxResult("0","",dataJson);
	}
	
	@Override
	public AjaxResult query_equip_charge_status(String orderSn) {
		JSONObject paramJson=new JSONObject();
		paramJson.put("StartChargeSeq",orderSn);
		JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_equip_charge_status, query_token(), paramJson.toString());
		if (retJson.getInteger("Ret")==4002) {
			retJson=HttpUtil.chargePost(CommonUtil.query_equip_charge_status, update_token(), paramJson.toString());
		}
		JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		return new AjaxResult(CommonUtil.SUCCESS_CODE, CommonUtil.SUCCESS_MSG, dataJson);
	}
	
	@Override
	public AjaxResult query_equip_auth(String connectorID){
		String orderSn=CommonUtil.OperatorID+DateUtil.formatDate(new Date(),"yyyyMMddHHmmss")+StringUtil.getRandom(4);
		JSONObject paramJson=new JSONObject();
		paramJson.put("equipAuthSeq",orderSn);
		paramJson.put("connectorID",connectorID);
		JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_equip_auth, query_token(), paramJson.toString());
		if (retJson.getInteger("Ret")==4002) {
			retJson=HttpUtil.chargePost(CommonUtil.query_equip_auth, update_token(), paramJson.toString());
		}
		if(retJson.getInteger("Ret")!=0){
			return new AjaxResult(CommonUtil.ERROR_CODE,retJson.getString("Msg"));
		}
		JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		return new AjaxResult(dataJson.getString("SuccStat"),dataJson.getString("FailReason"),dataJson);
	}

	@SuppressWarnings("null")
	@Override
	public AjaxResult query_start_charge(Member logM,String connectorID) {
		//验证个人账号余额及企业余额及是否有待完成订单
		Firm firm=null;
		//验证用户是否有效
		if(logM.getEnabled()!=0){
			return new AjaxResult(CommonUtil.ERROR_CODE,"充电失败：用户不合法");
		}
		//验证用户是否审核
		if (logM.getReview()!=1) {
			return new AjaxResult(CommonUtil.ERROR_CODE,"充电失败：用户资料未通过审核");
		}
		if (logM.getType()==0) {//普通会员
			if (logM.getAccount()<=0) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"充电失败：账户余额不足");
			}
		}else {
			firm=firmMapper.queryById(logM.getFirm_id());
			//验证企业合同时间
			if (firm.getEnabled()!=0) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"充电失败：企业不合" +
						"法");
			}
			if (!(firm.getSign_date().before(new Date())&&firm.getEnd_date().after(new Date()))) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"充电失败：企业协议已过期");
			}
			if (firm.getLeft_amount()<=0) {
				return new AjaxResult(CommonUtil.ERROR_CODE,firm.getPay_type()==0?"充电失败：企业信用额度不足":"充电失败：企业账户余额不足");
			}
		}
		//验证是否有待处理的订单
		ChargeOrder valiOrder=chargeOrderMapper.queryValid(logM.getId());
		if (valiOrder!=null) {
			return new AjaxResult(CommonUtil.ERROR_CODE,"充电失败：您有一个待完成订单需要处理",valiOrder.getSn());
		}
		//设备认证
		AjaxResult equip_auth=query_equip_auth(connectorID);
		//认证成功开始充电
		if (equip_auth.getCode().equals("0")) {
			JSONObject equip_auth_json=JSON.parseObject(equip_auth.getData().toString());
			JSONObject paramJson=new JSONObject();
			paramJson.put("StartChargeSeq",equip_auth_json.getString("EquipAuthSeq"));
			paramJson.put("ConnectorID",equip_auth_json.getString("ConnectorID"));
			paramJson.put("QRCode","");
			JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_start_charge, query_token(), paramJson.toString());
			if (retJson.getInteger("Ret")==4002) {
				retJson=HttpUtil.chargePost(CommonUtil.query_start_charge, update_token(), paramJson.toString());
			}
			if(retJson.getInteger("Ret")!=0){
				return new AjaxResult(CommonUtil.ERROR_CODE,retJson.getString("Msg"));
			}
			JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
			//开始充电成功并创建订单
			if (dataJson.getInteger("SuccStat")==0) {
				chargeOrderMapper.add(new ChargeOrder(dataJson.getString("StartChargeSeq"),dataJson.getString("ConnectorID"),logM.getId(),logM.getFirm_id(),logM.getType()==0?1:firm.getPay_type()));
				logMapper.add(new OrderLog(StringUtil.getUuid(),equip_auth_json.getString("EquipAuthSeq"),null,logM.getId(),"创建订单"));
			}
			return new AjaxResult(dataJson.getString("SuccStat"),dataJson.getString("FailReason"),dataJson.getString("StartChargeSeq"));
		}
		//认证失败
		return equip_auth;
	}

	@Override
	public AjaxResult query_stop_charge(String orderSn) {
		ChargeOrder order=chargeOrderMapper.queryById(orderSn);
		JSONObject paramJson=new JSONObject();
		paramJson.put("StartChargeSeq",orderSn);
		paramJson.put("ConnectorID",order.getConnectorID());
		JSONObject retJson=HttpUtil.chargePost(CommonUtil.query_stop_charge, query_token(), paramJson.toString());
		if (retJson.getInteger("Ret")==4002) {
			retJson=HttpUtil.chargePost(CommonUtil.query_stop_charge, update_token(), paramJson.toString());
		}
		if(retJson.getInteger("Ret")!=0){
			return new AjaxResult(CommonUtil.ERROR_CODE,retJson.getString("Msg"));
		}
		JSONObject dataJson=JSON.parseObject(AESUtil.Decrypt(retJson.getString("Data"),CommonUtil.OperatorSecret));
		return new AjaxResult(dataJson.getString("SuccStat"),dataJson.getString("FailReason"),dataJson);
	}

}
