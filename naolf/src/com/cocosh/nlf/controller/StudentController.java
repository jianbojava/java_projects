package com.cocosh.nlf.controller;

import java.util.List;

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
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.service.StudentService;

@Controller
@RequestMapping("manage/student")
public class StudentController extends BaseController {
	@Autowired
	private StudentService service;

	@RequiresPermissions("manage:student:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Student> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "nlf/student/list";
	}
	
	@RequiresPermissions("manage:student:add")
	@RequestMapping(value="add/{user_id}",method=RequestMethod.GET)
	public String add(Model model,@PathVariable String user_id) {
		model.addAttribute("user_id", user_id);
		return "nlf/student/add";
	}

	@RequiresPermissions("manage:student:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Student po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:student:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("student",service.queryById(id));
		return "nlf/student/update";
	}

	@RequiresPermissions("manage:student:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Student po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:student:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequestMapping("queryByUserId/{user_id}")
	@ResponseBody
	public List<Student> queryByUserId(@PathVariable String user_id) {
		Student student=new Student();
		student.setUser_id(user_id);
		return service.queryByAll(student);
	}
}
