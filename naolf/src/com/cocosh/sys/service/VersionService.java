package com.cocosh.sys.service;
 
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.Version;

public interface VersionService {
	boolean add(Version po);
	boolean del(String ids);
	boolean update(Version po);
	Page<Version> queryPage(BaseConditionVO vo);
	Version queryById(String id);
	Version queryLast(String type);
}
