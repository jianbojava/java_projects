package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.sys.model.Dict;

public interface DictMapper {
	Integer add(Dict po);
	Integer del(String[] ids);
	Integer update(Dict po);
	List<Dict> queryAll();
	List<Dict> queryCAll(String code);
	String queryByCode(String code);
}
