package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Brand;
import com.cocosh.car.model.BrandType;

public interface BrandService {
	boolean add(Brand po);
	boolean del(Integer flg,String ids);
	boolean update(Brand po);
	Page<Brand> queryPage(BaseConditionVO vo);
	Brand queryById(String id);
	List<Brand> queryAllByType(String type);
	List<BrandType> queryByBId(String brandid);
}
