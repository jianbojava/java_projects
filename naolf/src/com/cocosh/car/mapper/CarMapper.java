package com.cocosh.car.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Car;

public interface CarMapper {
	Integer add(Car po);
	Integer del(Map<String,Object> map);
	Integer update(Car po);
	List<Car> queryPage(BaseConditionVO vo);
	List<Car> queryDyncPage(BaseConditionVO vo);
	List<Car> queryPageApp(BaseConditionVO vo);
	Car queryById(String id);
	Car queryByIdAPP(BaseConditionVO vo);
	List<Car> queryAll();
	List<Car> queryAllNoPage(BaseConditionVO vo);
}
