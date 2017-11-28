package com.cocosh.nlf.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Course;
import com.cocosh.sys.model.Depart;

public interface CourseMapper {
	Integer add(Course po);
	Integer del(Map<String, Object> map);
	Integer update(Course po);
	List<Course> queryPage(BaseConditionVO vo);
	Course queryById(String id);
	Course queryByString(String lesson_id);
	List<Course> queryWapList(Course po);
}
