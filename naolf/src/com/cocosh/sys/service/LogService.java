package com.cocosh.sys.service;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.Log;

public interface LogService {
	boolean add(Log log);
	boolean del(String ids);
	Page<Log> queryPage(BaseConditionVO vo);
}
