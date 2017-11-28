package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Lcomment;

public interface LcommentMapper {
	Integer add(Lcomment po);
	Integer del(String[] ids);
	Integer update(Lcomment po);
	List<Lcomment> queryPage(BaseConditionVO vo);
	Lcomment queryById(String id);
	Lcomment queryAll(Lcomment po);
	List<Lcomment> queryByUtId(String user_ticket_id);
}
