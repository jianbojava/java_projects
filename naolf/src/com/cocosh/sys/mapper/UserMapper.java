package com.cocosh.sys.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.sys.model.User;

public interface UserMapper {
	Integer add(User user);
	Integer del(Map<String,Object> map);
	Integer delete(String id);
	Integer update(User user);
	Integer updatePwdByMobile(User user);
	List<User> queryPage(BaseConditionVO vo);
	User queryLogin(User user);
	User checkUser(User user);
	User queryById(String id);
	List<User> queryByRole(String role_id);
	User queryByNumber(String number);
	List<User> queryByUsertype(User user);
	List<User> queryAllNumber();
	User loginByUserNumber(User u);//编号，和username登陆
	User loginByMobile(User u);//手机号登陆
	User queryByMobile(String mobile);
}
