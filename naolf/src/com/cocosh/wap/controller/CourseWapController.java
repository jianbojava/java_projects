package com.cocosh.wap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Course;
import com.cocosh.nlf.model.Lcomment;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.AppointService;
import com.cocosh.nlf.service.CourseService;
import com.cocosh.nlf.service.LcommentService;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.NorderService;
import com.cocosh.nlf.service.StudentService;
import com.cocosh.nlf.service.TcommentService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.DepartService;

@Controller
@RequestMapping("wap/course/")
public class CourseWapController extends BaseController {
	@Autowired
	private AppointService appointService;
	@Autowired
	private UserTicketService userTicketService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private NorderService norderService;
	@Autowired
	private LcommentService lcommentService;
	@Autowired
	private TcommentService tcommentService;
	@Autowired
	private DepartService departService;
	
	/*
	 * 课程列表
	 * */
	@RequestMapping("courseSearch")
	public String courseSearch(Model model) {
		User user=getWapUser();
		Norder  norder=new Norder();
		norder.setUser_id(user.getId());
		norder.setType(0);
		List<Norder> llist=norderService.querywap(norder);
		model.addAttribute("llist", llist);
		return "wap/course/courseSearch";
	}
	
	/*
	 * 卡券查询
	 * */
	@RequestMapping("cardCouponSearch")
	public String cardCouponSearch(Model model) {
		User user=getWapUser();
		Norder  norder=new Norder();
		norder.setUser_id(user.getId());
		norder.setType(1);
		List<Norder> tlist=norderService.querywap(norder);
		model.addAttribute("tlist", tlist);
		return "wap/course/cardCouponSearch";
	}
	
	/*
	 * 评价
	 * */
	@RequestMapping("comment")
	public String comment(Model model,String id) {
		Appoint appoint=new Appoint();
		appoint.setUser_ticket_id(id);
		appoint.setCourse_type(1);
		appoint.setStatus(100);
		List<Appoint> list=appointService.queryWapCoures(appoint);
		Lesson lesson=new Lesson();
		Course course=new Course();
		if(list.size()>0){
			appoint =list.get(0);
			lesson = lessonService.queryById(appoint.getLesson_id());
			course = courseService.queryById(appoint.getCourse_id());
		}else{
			UserTicket ut=userTicketService.queryById(id);
			if(ut!=null) lesson=lessonService.queryById(ut.getConn_id());
		}
		Lcomment lcomment=null;
		List<Lcomment> mlist = lcommentService.queryByUtId(id);
		if(mlist.size()>0) lcomment=mlist.get(0);
		model.addAttribute("lcomment", lcomment);
		model.addAttribute("course", course);
		model.addAttribute("appoint", appoint);
		model.addAttribute("lesson", lesson);
		return "wap/course/comment";
	}
	
	/*
	 * 课程详情(ut_id)
	 * */
	@RequestMapping("courseDetail_noReserve")
	public String courseDetail_noReserve(Model model,String id) {
		UserTicket ut=userTicketService.queryById(id);
		Lesson lesson=lessonService.queryById(ut.getConn_id());
		model.addAttribute("ut", ut);
		model.addAttribute("lesson", lesson);
		String appoint_id="";
		String student_id="";
		String course_id="";
//		if(ut.getStatus()==-3){//已经预约
			Appoint appoint=new Appoint();
			appoint.setUser_ticket_id(id);
			appoint.setCourse_type(1);
			appoint.setStatus(100);
			List<Appoint> list=appointService.queryWapCoures(appoint);
			if(list.size()>0){
				appoint_id=list.get(0).getId();
				student_id=list.get(0).getStudent_id();
				course_id=list.get(0).getCourse_id();
				
			}
//		}
	    Integer comm=0;
	    if(ut.getStatus()>=4){
	    	List<Lcomment> lcomment=lcommentService.queryByUtId(ut.getId());
	    	if(lcomment.size()>0) comm=1;//已经评价
	    }
	    model.addAttribute("comm", comm);
		model.addAttribute("appoint_id", appoint_id);
		model.addAttribute("student_id", student_id);
		model.addAttribute("course_id", course_id);
		return "wap/course/courseDetail_noReserve";
	}
	
	/*
	 * 课程详情-半结业
	 * */
	@RequestMapping("courseDetail_overHalf")
	public String courseDetail_overHalf() {
		return "wap/course/courseDetail_overHalf";
	}
	
	/*
	 * 课程详情-训练
	 * */
	@RequestMapping("courseDetail_train")
	public String courseDetail_train() {
		return "wap/course/courseDetail_train";
	}
	
	/*
	 * 预约-选择孩子
	 * */
	@RequestMapping("reserveChooseChild")//userticket的id和norder的id
	public String reserveChooseChild(Model model,String id,String n_id) {
//		List<Appoint> l = appointService.queryWapCh(n_id);//该订单存在预约记录
		String student_id=appointService.getStudentIdByNorderId(n_id);
		if(!StringUtil.isEmpty(student_id)&&!StringUtil.isEmpty(n_id)){
			String ut_id = id;
			UserTicket ut = userTicketService.queryById(ut_id);
			Course course = new Course();
			course.setLesson_id(ut.getConn_id());
			course.setStatus(1);
			course.setType(1);
			List<Course> list = courseService.queryWapList(course,ut_id);
			model.addAttribute("ut", ut);
			model.addAttribute("s_id", student_id);//学生id
			model.addAttribute("list", list);
			return "wap/course/reserveDevelop";
		}
		//第一次预约
		User user = getWapUser();
		Student student = new Student();
		student.setUser_id(user.getId());
		List<Student> list = studentService.queryByAll(student);
		model.addAttribute("list", list);//孩子
		model.addAttribute("user_ticket_id", id);
		return "wap/course/reserveChooseChild";
	}
	
	/*
	 * 开发预约
	 * */
	@RequestMapping("reserveDevelop")
	public String reserveDevelop(Model model,String s_id,String ut_id) {
		UserTicket ut = userTicketService.queryById(ut_id);
		Course course = new Course();
		course.setLesson_id(ut.getConn_id());
		course.setStatus(1);
		course.setType(1);
		List<Course> list = courseService.queryWapList(course,ut_id);
		model.addAttribute("ut", ut);
		model.addAttribute("s_id", s_id);//学生id
		model.addAttribute("course_type", 1);//类型
		model.addAttribute("list", list);
		return "wap/course/reserveDevelop";
	}
	
	/*
	 * 预约记录
	 * */
	@RequestMapping("reserveRecord/{user_ticket_id}/{status}")
	public String reserveRecord(Model model,@PathVariable String user_ticket_id,@PathVariable Integer status) {
		List<Appoint> list = appointService.queryWapRecord(user_ticket_id,status);
		model.addAttribute("list", list);
		model.addAttribute("status", status);
		return "wap/course/reserveRecord";
	}
	
	/*
	 * 预约详情
	 * */
	@RequestMapping("reserveDetail")
	public String reserveDetail(Model model,String id) {
		Course course = new Course();
		course.setId(id);
		course.setStatus(1);
		Course courses = courseService.queryWapclick(id);
		model.addAttribute("course", courses);
		Depart depart=departService.queryById(courses.getDepart_id());
		model.addAttribute("depart", depart);
		return "wap/course/reserveDetail";
	}
	
	
	/*
	 * 复训预约
	 * */
	@RequestMapping("reviewDevelop/{ut_id}")
	public String reviewDevelop(Model model,@PathVariable String ut_id) {
		UserTicket ut = userTicketService.queryById(ut_id);
		Course course = new Course();
		course.setLesson_id(ut.getConn_id());
		course.setStatus(1);
		course.setType(0);
		List<Course> list = courseService.queryWapList(course,ut_id);
		model.addAttribute("ut", ut);
		model.addAttribute("s_id", "");//学生id
		model.addAttribute("list", list);
		model.addAttribute("course_type", 0);//类型
		return "wap/course/reviewDevelop";
	}
	
	/*
	 * 扫码成功后跳转
	 * */
	@RequestMapping("coursesuccess/{appoint_id}")
	public String success(Model model,@PathVariable String appoint_id) {
		Appoint appoint=appointService.queryById(appoint_id);
		Norder norder=norderService.queryById(appoint.getNorder_id());
		Course course=courseService.queryById(appoint.getCourse_id());
		UserTicket ut=userTicketService.queryById(appoint.getUser_ticket_id());
		Student student=studentService.queryById(appoint.getStudent_id());
		model.addAttribute("userticket_sn", ut.getSn());
		model.addAttribute("lesson_name", course.getL_name());
		model.addAttribute("start_time", StringUtil.getLocalDataM(course.getStart_time()));
		model.addAttribute("stu_name", student.getName());
		model.addAttribute("stu_age", student.getAge());
		String ticket_sn="";
		if(norder.getType()==1){//卡券订单
			ticket_sn=norder.getTicket_sn();
		}
		model.addAttribute("ticket_sn",ticket_sn);
		model.addAttribute("course_type", course.getType());//0进阶，1集中
		return "wap/course/coursesuccess";
	}
	
	/*
	 * 查看教师评价
	 * */
	@RequestMapping("tcomment/{appoint_id}")
	public String tcomment(Model model,@PathVariable String appoint_id) {
		model.addAttribute("tcomment", tcommentService.queryByappoint_Id(appoint_id));
		return "wap/course/tcomment";
	}
	
	
}
