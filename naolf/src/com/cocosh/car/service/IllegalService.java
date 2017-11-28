package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.car.model.Illegal;
import com.cocosh.car.model.IllegalExport;

public interface IllegalService {
	void add();
	boolean update(Illegal po);
	Page<Illegal> queryPage(BaseConditionVO vo);
	List<IllegalExport> queryExport(BaseConditionVO vo);
}
