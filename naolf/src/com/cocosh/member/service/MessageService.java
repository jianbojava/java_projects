package com.cocosh.member.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Message;

public interface MessageService {
	boolean add(Message po);
	boolean del(String mem_id,String ids);
	boolean read(String mem_id,List<Message> list);
	List<Message> queryPageApp(BaseConditionVO vo);
	List<Message> queryWapAll(Message vo);
	Integer queryNoReadCount(String mem_id);
}
