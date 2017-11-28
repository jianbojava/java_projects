package com.cocosh.member.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Message;

public interface MessageMapper {
	Integer add(Message po);
	Integer updateRead(Map<String,Object> map);
	Integer updateDel(Map<String,Object> map);
	List<Message> queryPageApp(BaseConditionVO vo);
	List<Message> queryWapAll(Message vo);
	Integer queryNoReadCount(String mem_id);
}
