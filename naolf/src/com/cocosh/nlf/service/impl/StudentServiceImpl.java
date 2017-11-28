package com.cocosh.nlf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.StudentMapper;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.service.StudentService;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper mapper;

	@LogClass(module = "学生管理", method = "添加")
	@Override
	public boolean add(Student po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "学生管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "学生管理", method = "修改")
	@Override
	public boolean update(Student po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Student> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Student> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Student queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Student> queryByAll(Student student) {
		// TODO Auto-generated method stub
		return mapper.queryByAll(student);
	}

}
