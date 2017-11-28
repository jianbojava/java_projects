package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Firm;
import com.cocosh.sys.model.User;
import com.cocosh.car.model.Car;
import com.cocosh.car.model.Order;

public interface OrderMapper {
	Integer add(Order po);
	Integer del(Map<String,Object> map);
	Integer update(Order po);
	List<Order> queryPage(BaseConditionVO vo);
	List<Order> queryDyncPage(BaseConditionVO vo);
	Order queryById(String id);
	Order queryByIdApp(String id);
	List<Order> queryPageApp(BaseConditionVO vo);
	Order queryAmount(String id);
	Order queryValidDate(Order po);
	Order queryValidDateM(Order po);
	List<Order> queryExport(BaseConditionVO vo);
	List<Order> queryTimeExport(BaseConditionVO vo);
	Integer updateIsRead(String id);
	Integer queryNoReadCount(Integer type);
	
	List<Firm> queryFirmFromOrder(BaseConditionVO vo);
	List<User> queryDispFromOrder(BaseConditionVO vo);
	//车辆ID查找调度员手机号
	Car queryDispByCid(String id);
	//查询24小时内所有未发送短信订单(备车)
	List<Order> queryDispByOid0();
	Integer updateIsSms0(String id);
	//查询30分钟前所有未发送短信订单(交车)
	List<Order> queryDispByOid1();
	Integer updateIsSms1(String id);
	//查询超过取车时间20分钟未发送短信订单（用车提醒）
	List<Order> queryDispByOid2();
	Integer updateIsSms2(String id);
	//超过30分钟未付款订单自动取消
	List<String> queryCancelOrder();
	Integer cancelOrder(String id);
	//通过SN查找订单数目
	Integer queryCountBySn(String sn);
	Order queryBaseBySn(String sn);
	//查找企业当前全部进行中的订单保证金
	Double queryTotalDeposit(String firm_id);
}
