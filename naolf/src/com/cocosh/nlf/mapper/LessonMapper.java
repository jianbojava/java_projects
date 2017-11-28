package com.cocosh.nlf.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.car.model.Dot;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Lesson;

public interface LessonMapper {
	Integer add(Lesson po);
	Integer del(Map<String, Object> map);
	Integer update(Lesson po);
	List<Lesson> queryPage(BaseConditionVO vo);
	Lesson queryById(String id);
	List<Lesson> queryAll();
	List<Lesson> queryByAll();
	Lesson checkName(Lesson po);
	List<Lesson> queryByIds(String[] ids);
	Lesson queryLessonId(Lesson po);
}
