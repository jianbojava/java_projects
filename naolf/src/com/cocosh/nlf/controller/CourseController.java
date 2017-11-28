package com.cocosh.nlf.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.model.BrandType;
import com.cocosh.car.model.Parking;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Course;
import com.cocosh.nlf.service.CourseService;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DepartService;

@Controller//课程发布
@RequestMapping("manage/course")
public class CourseController extends BaseController {
	@Autowired
	private CourseService service;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private DepartService departService;

	@RequiresPermissions("manage:course:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Course> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("departs", departService.queryCenter());
		return "nlf/course/list";
	}
	
	@RequiresPermissions("manage:course:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		Depart depart = departService.queryById(user.getDepart_id());
		model.addAttribute("d", depart);
		model.addAttribute("departs",departService.queryCenter());
		model.addAttribute("lessons", lessonService.queryByAll());
		return "nlf/course/add";
	}

	@RequiresPermissions("manage:course:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Course po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:course:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("lessons", lessonService.queryByAll());
		Course  course=service.queryById(id);
		model.addAttribute("course",course);
		model.addAttribute("d", departService.queryById(course.getDepart_id()));
		model.addAttribute("departs", departService.queryCenter());
		return "nlf/course/update";
	}

	@RequiresPermissions("manage:course:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Course po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}



	/**
	 * 二级联动
	 * @param id
	 * @return
	 */
	@RequestMapping("address/{id}")
	@ResponseBody
	public List<Depart> address(@PathVariable String id){
		return departService.queryByDotId(id);
	}
	
	@RequiresPermissions(value={"manage:course:del","manage:course:cdel"},logical=Logical.OR)
	@RequestMapping("del/{flg}/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable Integer flg,@PathVariable String id) {
		if (service.del(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	@RequiresPermissions(value={"manage:course:del","manage:course:update"},logical=Logical.OR)
	@RequestMapping("status/{flg}/{id}")
	@ResponseBody
	public AjaxResult statusUpdate(@PathVariable Integer flg,@PathVariable String id) {
		if (service.statusUpdate(flg,id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	/*批量发布*/
	@RequiresPermissions("manage:appoint:update")
	@RequestMapping("allpublish/{flg}/{ids}")
	@ResponseBody
	public AjaxResult allpublish(@PathVariable Integer flg,@PathVariable String ids) {
		if (service.update(flg,ids)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	/*查看课程二维码*/
	@RequestMapping("qrcode/{id}")
	public String qrcode(@PathVariable String id,Model model) {
		model.addAttribute("id", id);
		return "nlf/course/qrcode";
	}
	
}
