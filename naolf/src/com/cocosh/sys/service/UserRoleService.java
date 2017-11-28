package com.cocosh.sys.service;

import java.util.List;

public interface UserRoleService {
	List<String> queryAvailable(String user_id);
}
