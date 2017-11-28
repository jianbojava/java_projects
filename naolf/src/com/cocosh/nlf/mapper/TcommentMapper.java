package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Tcomment;

public interface TcommentMapper {
	Integer add(Tcomment po);
	Integer del(String[] ids);
	Integer update(Tcomment po);
	List<Tcomment> queryPage(BaseConditionVO vo);
	Tcomment queryById(String id);
	Tcomment queryByappoint_Id(String appoint_id);
	String queryteacher(String appoint_id);
}
