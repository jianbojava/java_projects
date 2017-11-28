package com.cocosh.sys.service;

import java.util.List;

import com.cocosh.sys.model.Push;

public interface PushService {
	List<Push> query(Push push);
	boolean add(Push push);
	boolean del(String ids);
	List<Push> queryByMid(String mid);
	List<String> queryMobile(int application);
}
