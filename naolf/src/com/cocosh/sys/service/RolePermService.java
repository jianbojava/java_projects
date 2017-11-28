package com.cocosh.sys.service;

import java.util.List;

public interface RolePermService {
	List<String> queryAvailable(String user_id);
}
