package com.cocosh.member.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Level;

public interface LevelMapper {
	Integer add(Level po);
	Integer del(Map<String, Object> map);
	Integer update(Level po);
	List<Level> queryPage(BaseConditionVO vo);
	Level queryById(String id);
	Level queryMin();
}
