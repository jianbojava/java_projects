package com.cocosh.car.service;
 
import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Comment;

public interface CommentService {
	boolean add(Comment po);
	boolean del(Integer flg,String ids);
	boolean update(Comment po);
	Page<Comment> queryPage(BaseConditionVO vo,Integer type);
	List<Comment> queryPageApp(BaseConditionVO vo,Integer type);
	Map<String,Object> queryTotalAvg(String carid);
	Comment queryById(String id);
}
