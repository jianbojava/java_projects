package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Brand;

public interface BrandMapper {
	Integer add(Brand po);
	Integer del(Map<String,Object> map);
	Integer update(Brand po);
	List<Brand> queryPage(BaseConditionVO vo);
	Brand queryById(String id);
	List<Brand> queryAllByType(String type);
}
