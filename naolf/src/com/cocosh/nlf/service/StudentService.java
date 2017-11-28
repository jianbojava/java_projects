package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Student;

public interface StudentService {
	boolean add(Student po);
	boolean del(String ids);
	boolean update(Student po);
	Page<Student> queryPage(BaseConditionVO vo);
	Student queryById(String id);
	List<Student> queryByAll(Student student);
}
