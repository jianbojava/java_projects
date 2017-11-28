package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.sys.model.Log;

public interface LogMapper {
	Integer add(Log log);
	Integer del(String[] ids);
	List<Log> queryPage(BaseConditionVO vo);
}
