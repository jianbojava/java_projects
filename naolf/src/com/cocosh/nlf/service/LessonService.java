package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Lesson;

public interface LessonService {
	boolean add(Lesson po);
	boolean del(Integer flg, String ids);
	boolean update(Lesson po);
	Page<Lesson> queryPage(BaseConditionVO vo);
	Lesson queryById(String id);
	List<Lesson> queryAll();
	List<Lesson> queryByAll();
	Lesson checkName(String name);
	List<Lesson> queryByIds(String ids);
	Lesson queryLessonId(Lesson po);
}
