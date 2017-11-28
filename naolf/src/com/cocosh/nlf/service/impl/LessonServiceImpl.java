package com.cocosh.nlf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.AppointMapper;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.service.LessonService;

@Transactional
@Service
public class LessonServiceImpl implements LessonService {
	@Autowired
	private LessonMapper mapper;
	@Autowired
	private AppointMapper appointMapper;

	@LogClass(module = "课程管理管理", method = "添加")
	@Override
	public boolean add(Lesson po) {
		po.setId(StringUtil.getUuid());
		if(po.getTrain()==null||po.getTrain()<0){po.setTrain(0);}
		return mapper.add(po) > 0;
	}

	@LogClass(module = "课程管理管理", method = "修改")
	@Override
	public boolean update(Lesson po) {
		if(po.getTrain()==null||po.getTrain()<0){po.setTrain(0);}
		return mapper.update(po) > 0;
	}


    @LogClass(module = "课程管理管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    
	@Override
	public Page<Lesson> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Lesson> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public List<Lesson> queryByAll() {
		return mapper.queryByAll();
	}
	@Override
	public Lesson queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Lesson> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public Lesson checkName(String name) {
		Lesson lesson=new Lesson();
		lesson.setName(name);
		return mapper.checkName(lesson);
	}

	@Override
	public List<Lesson> queryByIds(String ids) {
		return mapper.queryByIds(ids.split(","));
	}
	

	@Override
	public Lesson queryLessonId(Lesson po) {
		Appoint appoint = new Appoint();
		appoint.setUser_ticket_id(po.getUt_id());
		appoint.setStatus(0);
		appoint = appointMapper.queryWapAll(appoint);
		po = mapper.queryLessonId(po);
		if(appoint!=null){
			po.setS_id(appoint.getStudent_id());
		}
		return po;
	}
}
