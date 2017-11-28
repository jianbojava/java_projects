package com.cocosh.car.service;
 
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Member;
import com.cocosh.car.model.Charge;

public interface ChargeService {
	boolean add(Charge po);
	boolean del(Integer flg,String ids);
	boolean update(Charge po);
	Page<Charge> queryPage(BaseConditionVO vo);
	Charge queryById(String id);
	
	/*********特来电对接*******/
	//创建/查询 token
	String query_token();
	String update_token();
	//查询充电站信息
	AjaxResult query_stations_info();
	//设备接口状态查询
	AjaxResult query_station_status();
	//查询统计信息
	AjaxResult query_station_stats();
	//查询充电状态
	AjaxResult query_equip_charge_status(String orderSn);
	//设备认证
	AjaxResult query_equip_auth(String connectorID);
	//开始充电
	AjaxResult query_start_charge(Member logM,String connectorID);
	//结束充电
	AjaxResult query_stop_charge(String orderSn);
}
