package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.sys.model.Version;

public interface VersionMapper {
	Integer add(Version po);
	Integer del(String[] ids);
	Integer update(Version po);
	List<Version> queryPage(BaseConditionVO vo);
	Version queryById(String id);
	Version queryLast();
	Version queryLastByType(String type);
}
