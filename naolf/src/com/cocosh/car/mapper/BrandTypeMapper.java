package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.BrandType;

public interface BrandTypeMapper {
	Integer add(BrandType po);
	Integer del(String[] ids);
	Integer update(BrandType po);
	List<BrandType> queryPage(BaseConditionVO vo);
	BrandType queryById(String id);
	Integer delByBrandId(String brand_id);
	List<String> queryIdsByBrandId(String brand_id);
	List<BrandType> queryByBrandId(String brand_id);
	String queryNamesByBrandId(String brand_id);
}
