package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Comment;

public interface CommentMapper {
	Integer add(Comment po);
	Integer del(Map<String, Object> map);
	Integer update(Comment po);
	List<Comment> queryPage(BaseConditionVO vo);
	List<Comment> queryStationPage(BaseConditionVO vo);
	List<Comment> queryPageApp(BaseConditionVO vo);
	List<Comment> queryStationPageApp(BaseConditionVO vo);
	Map<String,Object> queryTotalAvg(String carid);
	Comment queryById(String id);
}
