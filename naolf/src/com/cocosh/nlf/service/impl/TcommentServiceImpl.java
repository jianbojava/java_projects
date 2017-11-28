package com.cocosh.nlf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.AppointMapper;
import com.cocosh.nlf.mapper.TcommentMapper;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Tcomment;
import com.cocosh.nlf.service.TcommentService;

@Transactional
@Service
public class TcommentServiceImpl implements TcommentService {
	@Autowired
	private TcommentMapper mapper;
	@Autowired
	private AppointMapper appointMapper;

	@LogClass(module = "老师评论管理", method = "添加")
	@Override
	public boolean add(Tcomment po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "老师评论管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "老师评论管理", method = "修改")
	@Override
	public boolean update(Tcomment po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Tcomment> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Tcomment> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Tcomment queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public Tcomment queryByappoint_Id(String appoint_id) {
		return mapper.queryByappoint_Id(appoint_id);
	}

	@Override
	public String queryteacher(String appoint_id) {
		return mapper.queryteacher(appoint_id);
	}

	@Override
	public boolean addOrupdate(Tcomment po) {
		if(StringUtil.isEmpty(po.getId())){
			if(add(po)){
				Appoint appoint=new Appoint();
				appoint.setId(po.getAppoint_id());
				appoint.setTcomment(1);
				return  appointMapper.update(appoint)>0;
			}
			return false;
		}else{
			return update(po);
		}
	}
	
}
