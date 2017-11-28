package com.cocosh.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.excel.ExportExcel;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.member.model.MemberExportVo;
import com.cocosh.member.service.FirmService;
import com.cocosh.member.service.MemberService;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("manage/member")
public class MemberController extends BaseController {
	@Autowired
	private MemberService service;
	@Autowired
	private FirmService firmService;
	@Autowired
	private UserService userService;

	/**
	 * 普通会员
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:member:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		vo.setType("0");//普通会员
		Page<Member> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "member/list";
	}
	
	/**
	 * 企业会员
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:member:flist")
	@RequestMapping("flist")
	public String flist(BaseConditionVO vo, Model model) {
		dataAuth(vo);
		vo.setType("1");//企业会员
		Page<Member> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("firms",service.queryFirmFromMem(vo));
		return "member/flist";
	}
	
	@RequiresPermissions(value={"manage:member:update","manage:member:fupdate"},logical=Logical.OR)
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		Member m=service.queryById(id);
		model.addAttribute("member",m);
		if(m.getType()==1){
			model.addAttribute("firm", firmService.queryAll());
		}else {
			model.addAttribute("sales",userService.queryByRole(CommonUtil.ROLE_SALE));
		}
		return "member/update";
	}

	@RequiresPermissions(value={"manage:member:update","manage:member:fupdate"},logical=Logical.OR)
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Member po) {
		if(!StringUtil.isEmpty(po.getMobile())){
			Member m=service.queryById(po.getId());
			if(!m.getMobile().equals(po.getMobile())){
				Member m1=service.queryByMobile(po.getMobile());//手机号已被注册
				if(m1!=null) return  new AjaxResult("-1");
			}
			if (service.update(po)) {
				return new AjaxResult("0");
			}
		}
		return new AjaxResult("1");
	}
	

	@RequiresPermissions(value={"manage:member:review","manage:member:freview"},logical=Logical.OR)
	@RequestMapping("review/{flg}/{id}")
	@ResponseBody
	public AjaxResult review(@PathVariable Integer flg,@PathVariable String id) {
		if (service.review(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions(value={"manage:member:add","manage:member:fadd"},logical=Logical.OR)
	@RequestMapping("add/{type}")
	public String add(@PathVariable Integer type,Model model) {
		if(type==1){
			model.addAttribute("firm", firmService.queryAll());
		}else {
			model.addAttribute("sales",userService.queryByRole(CommonUtil.ROLE_SALE));
		}
		model.addAttribute("type", type);
		return "member/add";
	}

	@RequiresPermissions(value={"manage:member:add","manage:member:fadd"},logical=Logical.OR)
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Member po) {
		if(!StringUtil.isEmpty(po.getMobile())){
			Member m=service.queryByMobile(po.getMobile());//手机号已被注册
			if(m!=null)return  new AjaxResult("-1");
			if(service.add(po))return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions(value={"manage:member:del","manage:member:fdel"},logical=Logical.OR)
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.enabled(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/**
	 * 充值
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("manage:member:recharge")
	@RequestMapping("recharge/{id}")
	public String recharge(@PathVariable String id,Model model) {
		Member m=service.queryById(id);
		model.addAttribute("member",m);
		return "member/recharge";
	}

	@RequiresPermissions("manage:member:recharge")
	@RequestMapping("recharge")
	@ResponseBody
	public AjaxResult recharge(Member po) {
		if (service.recharge(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:member:import")
	@RequestMapping("import/{suffix}")
	@ResponseBody
	public AjaxResult memImport(@PathVariable String suffix,HttpServletRequest req){
		return service.memImport(suffix,req);
	}
	
	// 导出Excel
	@RequiresPermissions("manage:member:export")
	@RequestMapping("export")
	public void export(BaseConditionVO vo, HttpServletResponse response) {
		try {
			new ExportExcel(MemberExportVo.class).setData(service.queryExport(vo)).write(response,"普通会员"+ DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + ".xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 假删除
	 * @param flg
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value={"manage:member:del_flg","manage:member:fdel_flg"},logical=Logical.OR)
	@RequestMapping("del_flg/{id}")
	@ResponseBody
	public AjaxResult del_flg(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:member:bind")
	@RequestMapping("syncEvcard/{id}")
	@ResponseBody
	public AjaxResult syncEvcard(@PathVariable String id){
		return service.syncEvcard(id);
	}
	
	@RequiresPermissions("manage:member:unbind")
	@RequestMapping("removeEvcard/{id}")
	@ResponseBody
	public AjaxResult removeEvcard(@PathVariable String id){
		return service.removeEvcard(id);
	}
	
	@RequiresPermissions("manage:member:check")
	@RequestMapping("checkEvcard/{id}")
	@ResponseBody
	public AjaxResult checkEvcard(@PathVariable String id){
		return service.checkEvcard(id);
	}
	
	@RequiresPermissions("manage:member:change")
	@RequestMapping("changeEvcard/{id}")
	@ResponseBody
	public AjaxResult changeEvcard(@PathVariable String id){
		return service.changeEvcard(id);
	}
	
	/**
	 * 设置个人信息
	 */
	@RequestMapping("setUp")
	@ResponseBody
	public AjaxResult setUp(HttpServletRequest req){
		User user=userService.queryById(getUser().getId());
		String oriPwd=req.getParameter("oriPwd");
		String newPwd=req.getParameter("newPwd");
		String rePwd=req.getParameter("rePwd");
		if (!user.getPassword().equals(SecurityUtil.md5(oriPwd))) {
			return new AjaxResult("1","修改失败：原密码不正确");
		}
		if (!newPwd.equals(rePwd)) {
			return new AjaxResult("1","修改失败：确认密码不正确");
		}
		User upd=new User();
		upd.setId(user.getId());
		upd.setHead_img(req.getParameter("headImg"));
		upd.setPassword(SecurityUtil.md5(newPwd));
		userService.modifyUser(upd);
		return new AjaxResult("0","修改成功,请重新登录");
	}
}
