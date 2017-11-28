package com.cocosh.car.service.impl;

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
import com.cocosh.car.mapper.CommentMapper;
import com.cocosh.car.model.Comment;
import com.cocosh.car.service.CommentService;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper mapper;

	@LogClass(module = "评论管理", method = "添加")
	@Override
	public boolean add(Comment po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "评论管理", method = "删除")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "评论管理", method = "修改")
	@Override
	public boolean update(Comment po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Comment> queryPage(BaseConditionVO vo,Integer type) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		if (type==0) {
			mapper.queryPage(vo);
		}else {
			mapper.queryStationPage(vo);
		}
		Page<Comment> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Comment queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public List<Comment> queryPageApp(BaseConditionVO vo,Integer type) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		if (type==0) {
			mapper.queryPageApp(vo);
		}else {
			mapper.queryStationPageApp(vo);
		}
		Page<Comment> page=PaginationInterceptor.endPage();
		return page.getResult();
	}

	@Override
	public Map<String, Object> queryTotalAvg(String carid) {
		return mapper.queryTotalAvg(carid);
	}
	
}
