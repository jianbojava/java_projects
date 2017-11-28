package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.sys.model.Push;

public interface PushMapper {
	List<Push> query(Push push);
	Integer queryCount(Push push);
	Integer add(Push push);
	Integer del(String[] ids);
	List<Push> queryByMid(String mid);
	List<String> queryMobile(int application);
}
