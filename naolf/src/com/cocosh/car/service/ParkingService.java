package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Parking;

public interface ParkingService {
	boolean add(Parking po);
	boolean del(Integer flg,String ids);
	boolean update(Parking po);
	Page<Parking> queryPage(BaseConditionVO vo);
	Parking queryById(String id);
	List<Parking> queryAll();
	List<Parking> queryByDotId(String dot_id);
	Parking queryDistanceByVo(BaseConditionVO vo);
}
