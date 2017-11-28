package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Lcomment;

public interface LcommentService {
	boolean add(Lcomment po);
	boolean del(String ids);
	boolean update(Lcomment po);
	Page<Lcomment> queryPage(BaseConditionVO vo);
	Lcomment queryById(String id);
	boolean addWap(Lcomment po);
	List<Lcomment> queryByUtId(String user_ticket_id);
}
