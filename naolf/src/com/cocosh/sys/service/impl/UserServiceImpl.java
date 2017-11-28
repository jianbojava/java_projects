package com.cocosh.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.mapper.ActivationMapper;
import com.cocosh.nlf.mapper.UserScoreInfoMapper;
import com.cocosh.nlf.model.Activation;
import com.cocosh.sys.mapper.DepartMapper;
import com.cocosh.sys.mapper.RoleMapper;
import com.cocosh.sys.mapper.UserDepartMapper;
import com.cocosh.sys.mapper.UserMapper;
import com.cocosh.sys.mapper.UserRoleMapper;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.Role;
import com.cocosh.sys.model.User;
import com.cocosh.sys.model.UserDepart;
import com.cocosh.sys.model.UserRole;
import com.cocosh.sys.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserDepartMapper userDepartMapper;

	@Autowired
	private UserScoreInfoMapper userScoreInfoMapper;
	@Autowired
	private ActivationMapper activationMapper;
	@Autowired
	private DepartMapper departMapper;

	@LogClass(module = "用户管理", method = "添加")
	@Override
	public boolean add(User user) {

		user.setId(StringUtil.getUuid());
		user.setPassword(SecurityUtil.md5("123456"));
		user.setType("0");
		user.setEnabled(0);
		user.setNumber(createNumber());

		try {
			// 可逆加密
			if (user.getBank_no() != null) {
				user.setBank_no(SecurityUtil.encrypt(user.getBank_no()));
			}

			if (user.getCard() != null) {
				user.setCard(SecurityUtil.encrypt(user.getCard()));
			}

			if (user.getMobile() != null) {
				user.setMobile(SecurityUtil.encrypt(user.getMobile()));
			}
		} catch (Exception e) {

		}

		userMapper.add(user);
		return true;
	}

	public boolean addWap(User user) {
		if (StringUtil.isEmpty(user.getId())) {
			user.setId(StringUtil.getUuid());
		}
		user.setNumber(createNumber());
		if (StringUtil.isEmpty(user.getPassword())) {
			user.setPassword("123456");
		}
		user.setIs_locked(0);//用户状态 0正常 1锁定
		user.setDepart_id(departMapper.queryParent().getId());
		user.setPassword(SecurityUtil.md5(user.getPassword()));
		user.setType("4");// 用户类型 消费者or会员
		user.setUser_type(2);//用户注册  类型用户
		user.setEnabled(1);
        user.setDispacth_grade(4);//提成等级
		try {
			// 可逆加密
			if (user.getBank_no() != null) {
				user.setBank_no(SecurityUtil.encrypt(user.getBank_no()));
			}

			if (user.getCard() != null) {
				user.setCard(SecurityUtil.encrypt(user.getCard()));
			}

			if (user.getMobile() != null) {
				user.setMobile(SecurityUtil.encrypt(user.getMobile()));
			}
		} catch (Exception e) {

		}

		userMapper.add(user);
		return true;
	}

	@LogClass(module = "用户管理", method = "删除")
	@Override
	public boolean del(Integer flg, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return userMapper.del(map) > 0;
	}

	@LogClass(module = "用户管理", method = "修改")
	@Override
	public boolean update(User user) {
//		if (user.getIs_reset() == 1) {
//			user.setPassword(SecurityUtil.md5("123456"));
//		}
//		user.setType("0");

		try {
			// 可逆加密
			if (user.getBank_no() != null) {
				user.setBank_no(SecurityUtil.encrypt(user.getBank_no()));
			}

			if (user.getCard() != null) {
				user.setCard(SecurityUtil.encrypt(user.getCard()));
			}

			if (user.getMobile() != null) {
				user.setMobile(SecurityUtil.encrypt(user.getMobile()));
			}
		} catch (Exception e) {

		}

		userMapper.update(user);
		if (user.getRoles() != null) {
			// 删除user_role关联
			UserRole ur = new UserRole();
			ur.setUserId(user.getId());
			userRoleMapper.del(ur);
			// 添加user_role关联
			String[] role_ids = user.getRoles().split(",");
			for (int i = 0; i < role_ids.length; i++) {
				ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(role_ids[i]);
				userRoleMapper.add(ur);
			}
		}
		if (user.getDeparts() != null) {
			// 删除user_depart关联
			UserDepart ud = new UserDepart();
			ud.setUser_id(user.getId());
			userDepartMapper.del(ud);
			// 添加user_depart关联
			String[] depart_ids = user.getDeparts().split(",");
			for (int i = 0; i < depart_ids.length; i++) {
				ud = new UserDepart();
				ud.setUser_id(user.getId());
				ud.setDepart_id(depart_ids[i]);
				userDepartMapper.add(ud);
			}
		}
		return true;
	}

	@LogClass(module = "用户管理", method = "审核")
	@Override
	public AjaxResult check(User user) {
		if(user.getEnabled()==2){//审核被拒绝，删除该用户
			Integer result=userMapper.update(user);
			if(result>0) {
				userMapper.delete(user.getId());
				return new AjaxResult("0", "");
			}
		}
		if(user.getEnabled()==1){//审核成功；先验证，然后修改
			User upuser=userMapper.queryById(user.getId());
			if(upuser!=null){
				if(StringUtil.isEmpty(upuser.getDepart_id()))
						return new AjaxResult("-1","部门不可以为空");
				if(upuser.getUser_type()==2){//0员工，1合伙人，2会员     
					if(StringUtil.isEmpty(upuser.getRefer_number()))
						return new AjaxResult("-1","推荐人不可以为空");
					if(upuser.getDispacth_grade()==null) 
						return new AjaxResult("-1","提成身份等级不可以为空");
				}
				if(upuser.getUser_type()==1){
					if(upuser.getDispacth_grade()==null)
						return new AjaxResult("-1","提成身份等级不可以为空");
					if(upuser.getDispacth_grade()!=1&&StringUtil.isEmpty(upuser.getRefer_number())) 
						return new AjaxResult("-1","推荐人不可以为空");
					Depart depart=departMapper.queryById(upuser.getDepart_id());
					if(depart==null||depart.getPerformance_ind()!=1)
						return new AjaxResult("-1","合伙人必须是绩效部门");
				}
				if(upuser.getUser_type()==0){
					Depart depart=departMapper.queryById(upuser.getDepart_id());
					if(depart!=null&&depart.getPerformance_ind()==1){//业绩部门
						if(upuser.getDispacth_grade()==null)
							return new AjaxResult("-1","提成身份等级不可以为空");
						if(upuser.getDispacth_grade()!=1&&StringUtil.isEmpty(upuser.getRefer_number())) 
							return new AjaxResult("-1","推荐人不可以为空");
					}
				}
				Integer result=userMapper.update(user);
                if(result>0) return new AjaxResult("0", "");
			}
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"操作失败");
	}
	@Override
	public boolean updatePwdByMobile(User user) {
		try {
			// 修改密码  手机号加密
			if (user.getMobile() != null) {
				user.setMobile(SecurityUtil.encrypt(user.getMobile()));
			}
		} catch (Exception e) {

		}
		System.out.println(user.getMobile());
		return userMapper.updatePwdByMobile(user) > 0;
	}

	@Override
	public Page<User> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(), vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		vo.setUser_id(user.getId());
		userMapper.queryPage(vo);
		Page<User> page = PaginationInterceptor.endPage();
		List<String> names = null;
		for (User u : page.getResult()) {
			List<Role> roles = roleMapper.queryNameByUserId(u.getId());
			names = new ArrayList<String>();
			for (int i = 0; i < roles.size(); i++) {
				names.add(roles.get(i).getName());
			}
			u.setRole_names(names);

			try {
				// 转义身份证，电话，银行卡
				if (u.getBank_no() != null) {
					u.setBank_no(SecurityUtil.decrypt(u.getBank_no()));
				}
				if (u.getCard() != null) {
					u.setCard(SecurityUtil.decrypt(u.getCard()));
				}
				if (u.getMobile() != null) {
					u.setMobile(SecurityUtil.decrypt(u.getMobile()));
				}
			} catch (Exception e) {

			}

		}
		return page;
	}

	@Override
	public User queryById(String id) {
		User user = userMapper.queryById(id);
		if (user != null) {
			user.setRoles(userRoleMapper.queryRolesByUser(id));

			try {
				// 转义身份证，电话，银行卡
				if (user.getBank_no() != null) {
					user.setBank_no(SecurityUtil.decrypt(user.getBank_no()));
				}
				if (user.getCard() != null) {
					user.setCard(SecurityUtil.decrypt(user.getCard()));
				}
				if (user.getMobile() != null) {
					user.setMobile(SecurityUtil.decrypt(user.getMobile()));
				}
			} catch (Exception e) {

			}
		}
		return user;
	}

	@Override
	public User queryByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		// List<User> list = userMapper.query(user);
		// if (list != null && list.size() > 0) {
		// return list.get(0);
		// }
		return null;
	}

	@LogClass(module = "用户管理", method = "修改密码")
	@Override
	public boolean updatePwd(String id, String password, String newPassword,
			String head_img) {
		User user = this.queryById(id);
		if (!user.getPassword().equals(password)) {
			return false;
		} else {
			user.setPassword(newPassword);
			user.setHead_img(head_img);
			return userMapper.update(user) > 0 ? true : false;
		}
	}

	@Override
	public User queryLogin(String username, String password) {
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		return userMapper.queryLogin(u);
	}

	@Override
	public User loginWap(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User u=userMapper.loginByUserNumber(user);
		if(u==null){
			try {
				user.setMobile(SecurityUtil.encrypt(username));
			} catch (Exception e) {
				e.printStackTrace();
			}
			u=userMapper.loginByMobile(user);
		}
		return u;
	}

	@Override
	public boolean updateIp(String ip, String id) {
		User u = new User();
		u.setLogin_ip(ip);
		u.setId(id);
		return userMapper.update(u) > 0;
	}

	@Override
	public User checkUser(User user) {
		return userMapper.checkUser(user);
	}

	@Override
	public boolean modifyUser(User user) {
		return userMapper.update(user) > 0;
	}

	@Override
	public List<User> queryByRole(String role_id) {
		return userMapper.queryByRole(role_id);
	}

	@Override
	public User queryByNumber(String number) {
		return userMapper.queryByNumber(number);
	}

	@Override
	public List<User> queryByUsertype(Integer user_type) {
		User user = new User();
		user.setUser_type(user_type);
		return userMapper.queryByUsertype(user);
	}

	@Override
	public List<User> queryAllNumber() {
		// TODO Auto-generated method stub
		return userMapper.queryAllNumber();
	}

	@Override
	public User queryByMobile(String mobile) {
		try {
			mobile=SecurityUtil.encrypt(mobile);
		} catch (Exception e) {
		}
		return userMapper.queryByMobile(mobile);
	}

	public String createNumber() {
		String number = StringUtil.getRandom(8);
		if (userMapper.queryByNumber(number) != null) {
			createNumber();
		}
		return number;
	}

	/* 前端使用激活码注册员工或合伙人 */
	@Override
	public AjaxResult addWapPartner(User po, String number) {
		po.setId(StringUtil.getUuid());
		// 验证推荐人
		String refer_number = po.getRefer_number();
		if (!StringUtil.isEmpty(refer_number)) {
			User puser = userMapper.queryByNumber(refer_number);
			if (puser == null) {
				return new AjaxResult(CommonUtil.ERROR_CODE, "推荐人不存在", null);
			}
		}
		// 修改激活码被使用
		Activation activation = activationMapper.checkNumber(number);
		if (activation == null)
			return new AjaxResult(CommonUtil.ERROR_CODE, "激活码不存在", null);
		if (activation.getEnabled() != null && activation.getEnabled() == 1)
			return new AjaxResult(CommonUtil.ERROR_CODE, "激活码已经被使用", null);
		activation.setEnabled(1);
		activation.setMember_id(po.getId());
		activationMapper.update(activation);
		po.setEnabled(0);
		po.setNumber(createNumber());
		// if(StringUtil.isEmpty(po.getPassword())) po.setPassword("123456");
		po.setIs_locked(0);
		if (StringUtil.isEmpty(po.getDepart_id()))
			po.setDepart_id(departMapper.queryParent().getId());
		po.setPassword(SecurityUtil.md5(po.getPassword()));
		po.setType("4");// 用户类型 消费者or会员

		try {

			if (po.getBank_no() != null) {
				po.setBank_no(SecurityUtil.encrypt(po.getBank_no()));
			}

			if (po.getCard() != null) {
				po.setCard(SecurityUtil.encrypt(po.getCard()));
			}

			if (po.getMobile() != null) {
				po.setMobile(SecurityUtil.encrypt(po.getMobile()));
			}
		} catch (Exception e) {
			return new AjaxResult(CommonUtil.ERROR_CODE, "加密失败", null);
		}

		userMapper.add(po);
		return new AjaxResult(CommonUtil.SUCCESS_CODE, "信息已提交至审核", null);
	}
}
