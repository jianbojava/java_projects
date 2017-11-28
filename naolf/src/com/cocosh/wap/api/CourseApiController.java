package com.cocosh.wap.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Course;
import com.cocosh.nlf.model.Lcomment;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.service.AppointService;
import com.cocosh.nlf.service.CourseService;
import com.cocosh.nlf.service.LcommentService;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.StudentService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.Regions;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DepartService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("courseApi/")
public class CourseApiController extends BaseController {
	Map<String, Object> dataMap=null;
	@Autowired
	private UserTicketService userTicketService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserService userService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private LcommentService lcommentService;
	@Autowired
	private DepartService departService;
	@Autowired
	private CourseService courseService;

	/* 
	 * 课程预约
	 * */
	@RequestMapping("addappoint")
	@ResponseBody
	public AjaxResult addappoint(Appoint po){
		User user = getWapUser();
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",dataMap);
		}
		return appointService.addWap(po);
	}
	
	/* 
	 * 选择孩子验证
	 * */
	@RequestMapping("studentVe")
	@ResponseBody
	public AjaxResult studentVe(String id){
		User user = getWapUser();
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",dataMap);
		}
		Student student = new Student();
		student.setUser_id(user.getId());
		user = userService.queryById(user.getId());
		if(StringUtil.isEmpty(user.getUsername())||StringUtil.isEmpty(user.getName())
				||StringUtil.isEmpty(user.getMobile())||StringUtil.isEmpty(user.getEmail())||StringUtil.isEmpty(user.getProvince_name())
				||StringUtil.isEmpty(user.getCard())||user.getGender()==null||user.getAge()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请先完善个人信息！",dataMap);}
		List<Student> list = studentService.queryByAll(student);
		if(list.size()<1){
			return new AjaxResult(CommonUtil.ERROR_CODE,"请先完善孩子信息！",dataMap);
		}
		return new AjaxResult(CommonUtil.SUCCESS_CODE,"感谢您的支持",dataMap);
	}
	
	

	/* 
	 * 取消预约
	 * */
	@RequestMapping("cancelappoint")
	@ResponseBody
	public AjaxResult cancelappoint(Appoint po){
		User user=getWapUser();
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null); 
		}
		return appointService.updateWap(po);
	}
	
	/* 
	 * 点击上课id
	 * */
	@RequestMapping("study")
	@ResponseBody
	public AjaxResult study(Appoint po){
		User user=getWapUser();
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null); 
		}
		return appointService.study(po);
	}
	
	/* 
	 * 复训上课（测试）user_ticket_id,course_id
	 * */
	@RequestMapping("review")
	@ResponseBody
	public AjaxResult review(Appoint po){
		User user=getWapUser();
		if(user==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null); 
		}
		po.setStatus(100);
		List<Appoint> list=appointService.queryWapCoures(po);
		if(list.size()<=0)  return new AjaxResult(CommonUtil.ERROR_CODE,"上课失败,没有预约记录",null); 
		po=list.get(0);
		return appointService.study(po);
	}
	
	

	/* 
	 * 课程评价
	 * */
	@RequestMapping("services")
	@ResponseBody
	public AjaxResult services(Lcomment po){
		if(getWapUser()==null){
			return new AjaxResult(CommonUtil.ERROR_CODE,"登陆超时",null); 
		}
		if(lcommentService.addWap(po)){
			return new AjaxResult(CommonUtil.SUCCESS_CODE,"感谢您的支持",dataMap);
		}else{
			return new AjaxResult(CommonUtil.ERROR_CODE,"评价失败,请稍后再试。",dataMap);
		}
	}
	
	/* 
	 * 查询是否已经评论
	 * */
	@RequestMapping("validcomment/{user_ticket_id}")
	@ResponseBody
	public Integer validcomment(@PathVariable String user_ticket_id){
		Integer comm=0;
		List<Lcomment> lcomment=lcommentService.queryByUtId(user_ticket_id);
		if(lcomment.size()>0) comm=1;
		return comm;
	}
	
	@RequestMapping("departRegions/{pid}")
	@ResponseBody
	public List<Regions> regions(@PathVariable String pid) {
		return departService.queryRegions(pid);
	}
	
	@RequestMapping("queryDepart")
	@ResponseBody
	public List<Depart> queryDepart(Depart depart) {
		List<Depart> list= departService.queryByRegions(depart);
		return list;
	}
	
	/*根据课程查询省市区和depart的可预约的部门*/
	@RequestMapping("queryCourse/{user_ticket_id}")
	@ResponseBody
	public List<Course> develop(@PathVariable String user_ticket_id,Course course){
		course.setStatus(1);
		List<Course> list=courseService.queryWapList(course,user_ticket_id);;
		return list;
	}
	
}
