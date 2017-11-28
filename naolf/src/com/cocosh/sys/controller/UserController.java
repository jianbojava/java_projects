package com.cocosh.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.tree.TreeNode;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.service.UserScoreInfoService;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DepartService;
import com.cocosh.sys.service.RoleService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("manage/user")
public class UserController extends BaseController {
	@Autowired
	private UserService service;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartService departService;

	@Autowired
	private UserScoreInfoService userScoreInfoService;

	@RequiresPermissions("manage:user:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<User> page = service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("depart", departService.queryParent());
		return "sys/user/list";
	}

	@RequiresPermissions("manage:user:add")
	@RequestMapping(value = "add/{user_type}", method = RequestMethod.GET)
	public String add(Model model, @PathVariable Integer user_type) {
		model.addAttribute("user_type", user_type);
		model.addAttribute("depart", departService.queryParent());
		model.addAttribute("users", service.queryByUsertype(null));
		return "sys/user/add";
	}

	@RequiresPermissions("manage:user:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(User user) {
		// 如果等级等于1，则需要判断这个部门是否已经存在提成等级等于1的员工，一个部门职能有一个提成等级1的员工
		String dunmy;
		if (user.getDispacth_grade() !=null && user.getDispacth_grade() == 1) {
			dunmy = userScoreInfoService.getDepart1grade(user.getDepart_id());

			if (dunmy != null) {
				return new AjaxResult("-1");
			}
		}
		if (service.add(user)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:user:del")
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg, @PathVariable String id) {
		if (service.del(flg, id)) {
			return new AjaxResult("0", "");
		}
		return new AjaxResult("1", "");
	}

	@RequiresPermissions(value={"manage:user:update","manage:user:list"},logical=Logical.OR)
	@RequestMapping("update/{id}/{is_update}")
	public String update(@PathVariable String id,@PathVariable Integer is_update, Model model) {
		User user=service.queryById(id);
		if(user.getBirthday()!=null){
			user.setAge(DateUtil.getAge(user.getBirthday()));
		}
		List<User> users=service.queryByUsertype(null);
		model.addAttribute("user", user);
		if(user!=null&&!StringUtil.isEmpty(user.getRefer_number())){
			User puser=service.queryByNumber(user.getRefer_number());
			if(puser!=null&&(puser.getIs_locked()!=0||puser.getEnabled()!=1)){//用户被锁定了
				users.add(puser);
			}
		}
		model.addAttribute("depart", departService.queryParent());
		model.addAttribute("is_update",is_update);//0，查看，1修改
		model.addAttribute("users", users);
		Integer isgrade=0;//是否是业绩部门
		if(user.getUser_type()==0){//员工
			Depart mydepart =departService.queryById(user.getDepart_id());
			if(mydepart!=null&&mydepart.getPerformance_ind()!=1){//是非绩效部门
				isgrade=1;
			}
		}
		model.addAttribute("isgrade", isgrade);
		return "sys/user/update";
	}

	@RequiresPermissions("manage:user:update")
	@RequestMapping("update/role/{id}")
	public String updateRoleDepart(@PathVariable String id, Model model) {
		model.addAttribute("user", service.queryById(id));
		model.addAttribute("roles", roleService.queryAll());
		TreeNode tree = departService.userDepartTree(id);
		model.addAttribute("tree", JSONArray.fromObject(tree));
		return "sys/user/update_role";
	}

	@RequiresPermissions("manage:user:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(User user) {
		// 如果等级等于1，则需要判断这个部门是否已经存在提成等级等于1的员工，一个部门职能有一个提成等级1的员工
		String dunmy;
		if (user.getDispacth_grade() != null && user.getDispacth_grade() == 1) {
			dunmy = userScoreInfoService.getDepart1grade(user.getDepart_id());

			if (dunmy != null && !user.getNumber().equals(dunmy)) {
				return new AjaxResult("-1");
			}
		}
		if (service.update(user)) {
			return new AjaxResult("0", "");
		}
		return new AjaxResult("1", "");
	}
	
	@RequiresPermissions("manage:user:check")
	@RequestMapping("check")
	@ResponseBody
	public AjaxResult check(User user) {
		return service.check(user);
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequiresPermissions("manage:user:changpwd")
	@RequestMapping(value = "changpwd", method = RequestMethod.GET)
	public String toModifyPwd(Model model) {
		User user = getUser();
		model.addAttribute("user", user);
		return "sys/user/modify_password";
	}

	@RequestMapping(value = "changpwd", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updatePassWord(String password, String newPassword,
			HttpServletRequest req) {
		User user = getUser();
		String file = req.getParameter("file");
		String head_img = null;
		// if(file == null){
		// head_img=uploadMore(req).split(",")[0];
		// }
		if (service.updatePwd(user.getId(), SecurityUtil.md5(password),
				SecurityUtil.md5(newPassword), head_img)) {
			return new AjaxResult("0", "修改成功");
		}
		return new AjaxResult("1", "原始密码错误");
	}

	// 根据编号查询会员
	@RequestMapping("queryByNumber/{number}")
	@ResponseBody
	public Map<String, Object> queryByNumber(@PathVariable String number) {
		User user = service.queryByNumber(number);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		return map;

	}

	// 根据编号查询会员
	@RequestMapping("queryByUsername/{username}")
	@ResponseBody
	public Map<String, Object> queryByUsername(@PathVariable String username) {
		User user = new User();
		user.setUsername(username);
		User quser = service.checkUser(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", quser);
		return map;

	}

	// 根据编号查询会员
	@RequestMapping("queryByMobile/{mobile}")
	@ResponseBody
	public Map<String, Object> queryByMobile(@PathVariable String mobile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", service.queryByMobile(mobile));
		return map;

	}
	
	
	@RequiresPermissions(value={"manage:user:list","manage:usercash:list"},logical=Logical.OR)
	@RequestMapping("account/{id}")
	public String userAccount(@PathVariable String id,Model model) {
		User user = service.queryById(id);
		model.addAttribute("user", user);
		return "nlf/usercash/useraccount";
	}

}
