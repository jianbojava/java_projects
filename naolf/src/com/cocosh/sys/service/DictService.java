package com.cocosh.sys.service;
 
import java.util.List;

import com.cocosh.sys.model.Dict;

public interface DictService {
	boolean add(Dict po);
	boolean del(String ids);
	boolean update(Dict po);
	List<Dict> queryAll();
	List<Dict> queryCAll(String code);
	String queryByCode(String code);
}
