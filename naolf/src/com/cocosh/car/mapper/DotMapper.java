package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Dot;

public interface DotMapper {
	Integer add(Dot po);
	Integer del(Map<String, Object> map);
	Integer update(Dot po);
	List<Dot> queryPage(BaseConditionVO vo);
	List<Dot> queryPageApp(BaseConditionVO vo);
	Dot queryById(String id);
	List<Dot> queryByAll();
}
