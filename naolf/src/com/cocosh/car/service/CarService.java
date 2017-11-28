package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Car;

public interface CarService {
	boolean add(Car po);
	boolean review(Integer flg,String ids);
	boolean enabled(Integer flg,String ids);
	boolean del(String ids);
	boolean update(Car po);
	Page<Car> queryPage(BaseConditionVO vo);
	Page<Car> queryDyncPage(BaseConditionVO vo);
	List<Car> queryPageApp(BaseConditionVO vo);
	Car queryById(String id);
	Car queryByIdAPP(BaseConditionVO vo);
	List<Car> queryAll();
	List<Car> queryAllNoPage(BaseConditionVO vo);
}
