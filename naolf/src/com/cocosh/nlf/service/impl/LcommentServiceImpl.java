package com.cocosh.nlf.service.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.LcommentMapper;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.Lcomment;
import com.cocosh.nlf.service.LcommentService;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class LcommentServiceImpl implements LcommentService {
	@Autowired
	private LcommentMapper mapper;
	@Autowired
	private UserTicketMapper userTicketMapper;
	@Autowired
	private LessonMapper lessonMapper;

	@LogClass(module = "课程评论管理", method = "添加")
	@Override
	public boolean add(Lcomment po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "课程评论管理", method = "删除")
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

	@LogClass(module = "课程评论管理", method = "修改")
	@Override
	public boolean update(Lcomment po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Lcomment> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Lcomment> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Lcomment queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public boolean addWap(Lcomment po) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		if(user==null) return false;
		List<Lcomment> commentlist=mapper.queryByUtId(po.getUser_ticket_id());
		if(commentlist.size()>0) return true;//已经评价过了
		po.setId(StringUtil.getUuid());
		po.setUser_id(user.getId());
		return mapper.add(po)>0;
	}

	@Override
	public List<Lcomment> queryByUtId(String user_ticket_id) {
		return mapper.queryByUtId(user_ticket_id);
	}
	
}
