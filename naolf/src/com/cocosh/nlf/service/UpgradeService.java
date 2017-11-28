package com.cocosh.nlf.service;
 
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Upgrade;

public interface UpgradeService {
	boolean add(Upgrade po);
	boolean del(String ids);
	boolean update(Upgrade po);
	Page<Upgrade> queryPage(BaseConditionVO vo);
	Upgrade queryById(String id);
	AjaxResult toUpgrade(Upgrade po);//卡券升级
	Upgrade queryByutid123(String user_ticket_id3);
	AjaxResult upgradebyjf(String ut_id1);//积分升级
	AjaxResult upgradeValid(String ut_id1);//升级验证
	boolean upgrade_success(String ut_sn,String sn,String trade_no,Integer pay_type);//原始券编号，订单号，交易号，支付方式
}
