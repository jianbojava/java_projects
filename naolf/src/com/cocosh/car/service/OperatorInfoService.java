package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.OperatorInfo;

public interface OperatorInfoService {
	boolean add(OperatorInfo po);
	boolean del(Integer flg,String ids);
	boolean update(OperatorInfo po);
	Page<OperatorInfo> queryPage(BaseConditionVO vo);
	OperatorInfo queryById(String id);
	List<OperatorInfo> queryAll();
}
