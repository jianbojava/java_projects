package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Appoint;

public interface AppointService {
	boolean add(Appoint po);
	boolean del(String ids);
	boolean update(Appoint po);
	Page<Appoint> queryPage(BaseConditionVO vo);
	Appoint queryById(String id);
	boolean statusUpdate(Integer flg, String id,Integer course_type);
	List<Appoint> queryAllId(String id);
	AjaxResult addWap(Appoint po);
	AjaxResult updateWap(Appoint po);//取消预约
	List<Appoint> queryWapRecord(String user_ticket_id,Integer status);//(查看记录)100预约列表，1000上课列表
	Appoint queryWapAll(Appoint po);
	List<Appoint> queryWapCoures(Appoint po);
	AjaxResult study(Appoint po);
	List<Appoint> queryWapCh(String n_id);
	String getStudentIdByNorderId(String n_id);//根据订单id得到预约的孩子的id（包含升级订单）;
	void addRemind();//添加消息提醒(第二天上课)
}
