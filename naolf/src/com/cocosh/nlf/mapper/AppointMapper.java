package com.cocosh.nlf.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Appoint;

public interface AppointMapper {
	Integer add(Appoint po);
	Integer del(Map<String, Object> map);
	Integer update(Appoint po);
	List<Appoint> queryPage(BaseConditionVO vo);
	List<Appoint> queryAllId(String id);
	List<Appoint> queryAllStatus(Appoint po);
	Appoint queryById(String id);
	String queryUtIds(String[] ids);//查询usertikcet ids通过appoint  ids查询
	String queryIds(Appoint po);//通过课程course_id查询ids
	Integer queryCount(Appoint po);
	List<Appoint> queryWapCoures(Appoint po);
	Appoint queryWapAll(Appoint po);
	Appoint queryLastAppoint(String user_ticket_id);
	Appoint queryLastClass(String user_ticket_id);
	List<Appoint>  queryRecord(Appoint po);//1000课程记录(status>=3);100预约记录(status!=2)
	Integer updateToReturn(String norder_id);
	String queryUserIds(String[] ids);//根据ids查询会员ids
	List<Appoint> queryRemind();//第二天上课的
	
}
