package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Dot;

public interface DotService {
	boolean add(Dot po);
	boolean del(Integer flg,String ids);
	boolean update(Dot po);
	Page<Dot> queryPage(BaseConditionVO vo);
	List<Dot> queryPageApp(BaseConditionVO vo);
	Dot queryById(String id);
	List<Dot> queryByAll();
}
