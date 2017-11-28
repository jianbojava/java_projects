package com.cocosh.nlf.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Tcomment;

public interface TcommentService {
	boolean add(Tcomment po);
	boolean del(String ids);
	boolean update(Tcomment po);
	Page<Tcomment> queryPage(BaseConditionVO vo);
	Tcomment queryById(String id);
	Tcomment queryByappoint_Id(String appoint_id);
	String queryteacher(String appoint_id);
	boolean addOrupdate(Tcomment po);
}
