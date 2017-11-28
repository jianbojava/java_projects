package com.cocosh.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.MessageMapper;
import com.cocosh.member.model.Message;
import com.cocosh.member.service.MessageService;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper mapper;

	@Override
	public boolean add(Message po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
	@Override
	public boolean del(String mem_id,String ids) {
		String[] idarr=ids.split(",");
		for(String id:idarr){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mem_id",mem_id);
			map.put("msg_id",id);
			mapper.updateDel(map);
		}
		return true;
	}
	
	@Override
	public boolean read(String mem_id,List<Message> list) {
		for(Message l:list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mem_id",mem_id);
			map.put("msg_id",l.getId());
			mapper.updateRead(map);
		}
		return true;
	}

	@Override
	public List<Message> queryPageApp(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPageApp(vo);
		Page<Message> page=PaginationInterceptor.endPage();
		return page.getResult();
	}

	@Override
	public Integer queryNoReadCount(String mem_id) {
		return mapper.queryNoReadCount(mem_id);
	}

	@Override
	public List<Message> queryWapAll(Message vo) {
		return mapper.queryWapAll(vo);
	}
	
}
