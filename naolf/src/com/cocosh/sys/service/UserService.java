package com.cocosh.sys.service;

import java.util.List;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.User;

public interface UserService {
	boolean add(User user);
	boolean del(Integer flg,String ids);
	boolean update(User user);
	AjaxResult  check(User user);//审核用户
	boolean updateIp(String ip,String id);
	Page<User> queryPage(BaseConditionVO vo);
	User queryById(String id);
	User queryByUsername(String username);
	User queryLogin(String username,String password);//  username或者number密码登录
	User loginWap(String username,String password);//wap端 mobile username,mobile,number密码登录
	boolean updatePwd(String id,String password,String newPassword,String head_img);
	User checkUser(User user);
	User queryByMobile(String mobile);
	
	boolean modifyUser(User user);
	
	List<User> queryByRole(String role_id);
	User queryByNumber(String number);
	List<User> queryByUsertype(Integer user_type);//0员工，1合伙人，2会员
	List<User> queryAllNumber();
	boolean updatePwdByMobile(User user);
	boolean addWap(User po);//添加会员
	AjaxResult addWapPartner(User po,String number);//添加合伙人或员工,激活码;
}
