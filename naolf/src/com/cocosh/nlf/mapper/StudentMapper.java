package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Student;

public interface StudentMapper {
	Integer add(Student po);
	Integer del(String[] ids);
	Integer update(Student po);
	List<Student> queryPage(BaseConditionVO vo);
	Student queryById(String id);
	List<Student> queryByAll(Student student);
}
