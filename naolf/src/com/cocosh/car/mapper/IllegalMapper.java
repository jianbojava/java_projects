package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.car.model.Illegal;
import com.cocosh.car.model.IllegalExport;

public interface IllegalMapper {
	Integer add(List<Illegal> list);
	Integer update(Illegal illegal);
	List<Illegal> queryPage(BaseConditionVO vo);
	List<IllegalExport> queryExport(BaseConditionVO vo);
}
