package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Course;
import com.cocosh.sys.model.Depart;

public interface CourseService {
	boolean add(Course po);
	boolean del(Integer flg, String ids);
	boolean update(Integer flg, String ids);
	boolean update(Course po);
	Page<Course> queryPage(BaseConditionVO vo);
	Course queryById(String id);
	boolean statusUpdate(Integer flg, String id);
	List<Depart> queryByBId(String parent_id);
	List<Course> queryWapList(Course po,String user_ticket_id);
	Course queryWapclick(String id);
}
