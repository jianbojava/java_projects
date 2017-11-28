package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.sys.model.UserDepart;
import com.cocosh.sys.model.UserRole;

public interface UserDepartMapper {
	Integer add(UserDepart uDepart);
	Integer del(UserDepart uDepart);
	List<UserDepart> query(UserDepart uDepart);
	String queryDepartsByUser(String uid);
}
