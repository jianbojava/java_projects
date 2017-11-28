package com.cocosh.nlf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.service.OrderLogService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.model.Ticket;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.AppointService;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.nlf.service.NorderService;
import com.cocosh.nlf.service.ReceiptService;
import com.cocosh.nlf.service.StudentService;
import com.cocosh.nlf.service.TicketService;
import com.cocosh.nlf.service.UserTicketService;
import com.cocosh.sys.service.DepartService;
import com.cocosh.sys.service.UserService;

@Controller
@RequestMapping("manage/norder")
public class NorderController extends BaseController {
	@Autowired
	private NorderService service;
	@Autowired
	private UserService userService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private OrderLogService logService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private UserTicketService uticketService;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private AppointService appointService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private DepartService departService;

	@RequiresPermissions("manage:norder:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<Norder> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("depart", departService.queryParent());
		return "nlf/norder/list";
	}
	/*新增订单*/
	@RequiresPermissions("manage:norder:add")
	@RequestMapping(value="add/{type}",method=RequestMethod.GET)
	public String add(Model model,@PathVariable Integer type) {
		if(type==0){//课程订单
			model.addAttribute("users", userService.queryByUsertype(null));
			model.addAttribute("lessons", lessonService.queryAll());
			model.addAttribute("finish", 0);
			return "nlf/norder/add";
		}else{//卡券订单
			model.addAttribute("users", userService.queryByUsertype(null));
			model.addAttribute("tickets", ticketService.queryAll());
			model.addAttribute("finish", 0);
			return "nlf/norder/addticket";
		}
		
	}
	/*新增已结业订单*/
	@RequiresPermissions("manage:norder:add")
	@RequestMapping(value="addfinish/{type}",method=RequestMethod.GET)
	public String addfinish(Model model,@PathVariable Integer type) {
		if(type==0){//课程订单
			model.addAttribute("users", userService.queryByUsertype(null));
			model.addAttribute("lessons", lessonService.queryAll());
			model.addAttribute("finish", 1);
			return "nlf/norder/add";
		}else{//卡券订单
			model.addAttribute("users", userService.queryByUsertype(null));
			model.addAttribute("tickets", ticketService.queryAll());
			model.addAttribute("finish", 1);
			return "nlf/norder/addticket";
		}
		
	}
	
	/*新增未结业订单*/
	@RequiresPermissions("manage:norder:add")
	@RequestMapping(value="addnofinish/{type}",method=RequestMethod.GET)
	public String nofinish(Model model,@PathVariable Integer type) {
		if(type==0){//课程订单
			model.addAttribute("users", userService.queryByUsertype(null));
			model.addAttribute("lessons", lessonService.queryAll());
			return "nlf/norder/nofinish";
		}else{//卡券订单
			model.addAttribute("users", userService.queryByUsertype(null));
			model.addAttribute("tickets", ticketService.queryAll());
			return "nlf/norder/nofinishticket";
		}
		
	}
   /*后台添加订单*/
	@RequiresPermissions("manage:norder:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Norder po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:norder:add")
	@RequestMapping("addfinish")
	@ResponseBody
	public AjaxResult addfinish(Norder po) {
		if (service.addfinish(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:norder:add")
	@RequestMapping("addnofinish")
	@ResponseBody
	public AjaxResult addnofinish(Norder po,HttpServletRequest request) {
		Integer result=0;
		if(po.getType()==0){//未结业课程订单
			Integer is_end=Integer.parseInt(request.getParameter("is_end"));
			String student_id=request.getParameter("student_id");
			Integer left_review=Integer.parseInt(request.getParameter("left_review"));
			result=service.addnofinish(po,is_end,student_id,left_review);
		}else{//未结业卡券订单（全部未结业）
			result=service.addnofinishticket(po);
		}
		return new AjaxResult(""+result);//>0操作成功==0操作失败<0付款失败
	}
	//未结业卡券订单（部分未结业）
	@RequiresPermissions("manage:norder:add")
	@RequestMapping("addnofinish_part")
	@ResponseBody
	public AjaxResult addnofinishpart(@RequestBody Norder po,HttpServletRequest request) {
		Integer result=0;
		result=service.addnofinishticket_part(po);
		return new AjaxResult(""+result);//>0操作成功==0操作失败<0付款失败
	}

	@RequiresPermissions("manage:norder:list")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		Norder norder=service.queryById(id);
		Integer status=norder.getStatus();
		model.addAttribute("norder",norder);
		model.addAttribute("user", userService.queryById(norder.getUser_id()));
		model.addAttribute("logs", logService.query(id));
		if(status==0||status==-1){
			if(norder.getType()==0){//课程
				List<Lesson> list= new ArrayList<Lesson>();
				list.add(lessonService.queryById(norder.getConn_id()));
				model.addAttribute("lessons",list);//lesson
			}else{//卡券
				Ticket ticket=ticketService.queryById(norder.getConn_id());
//				model.addAttribute("ticket", ticket);//ticket
				model.addAttribute("lessons", lessonService.queryByIds(ticket.getLesson_ids()));
			}
		}else{
		   model.addAttribute("ulessons", uticketService.queryLessonByNorderId(id));//课程信息userticket
//		   model.addAttribute("uticket", uticketService.queryTicketByNorderId(id));//卡券信息userticket
		}
		model.addAttribute("receipt", receiptService.queryByOrderId(id));
		return "nlf/norder/update";
	}

	@RequiresPermissions("manage:norder:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(Norder po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	
	@RequiresPermissions("manage:norder:update")
	@RequestMapping("cancel")
	@ResponseBody
	public AjaxResult cancel(Norder po) {
		if (service.cancel(po.getId())) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}
	//根据订单编号去订单详情
	@RequiresPermissions("manage:norder:list")
	@RequestMapping("queryBySn/{sn}")
	public String queryBySn(@PathVariable String sn,Model model) {
		Norder norder=service.queryBySn(sn);
		Integer status=norder.getStatus();
		model.addAttribute("norder",norder);
		model.addAttribute("user", userService.queryById(norder.getUser_id()));
		model.addAttribute("logs", logService.query(norder.getId()));
		if(status==0||status==-1){
			if(norder.getType()==0){//课程
				List<Lesson> list= new ArrayList<Lesson>();
				list.add(lessonService.queryById(norder.getConn_id()));
				model.addAttribute("lessons",list);//lesson
			}else{//卡券
				Ticket ticket=ticketService.queryById(norder.getConn_id());
//				model.addAttribute("ticket", ticket);//ticket
				model.addAttribute("lessons", lessonService.queryByIds(ticket.getLesson_ids()));
			}
		}else{
		   model.addAttribute("ulessons", uticketService.queryLessonByNorderId(norder.getId()));//课程信息userticket
//		   model.addAttribute("uticket", uticketService.queryTicketByNorderId(id));//卡券信息userticket
		}
		model.addAttribute("receipt", receiptService.queryByOrderId(norder.getId()));
		return "nlf/norder/update";
	}
	//去退款
	@RequiresPermissions("manage:norder:nreturn")
	@RequestMapping("nreturn/{id}")
	public String returnNorder(@PathVariable String id,Model model) {
		Norder norder=service.queryById(id);
		model.addAttribute("norder",norder);
		List<UserTicket> ulist=uticketService.queryLessonByNorderId(id);//课程信息userticket
		Double  total_price=0.0;
		Double end_price=0.0;
		for(UserTicket ut:ulist){
			total_price+=ut.getPrice();//全部课程总价
			if(ut.getStatus()>=4){
				end_price+=ut.getPrice();//未结业课程总价
			}
		}
		model.addAttribute("ulist", ulist);
		model.addAttribute("total_price", total_price);
		model.addAttribute("end_price", end_price);
		Integer return_point=0;
		if(end_price<norder.getPay_amount()) return_point=(int) ((norder.getPay_amount()-end_price)*CommonUtil.SCOREPERCENT);
		model.addAttribute("return_point", return_point);
		return "nlf/norder/nreturn";
	}
    //退款操作
	@RequiresPermissions("manage:norder:nreturn")
	@RequestMapping("nreturn")
	@ResponseBody
	public AjaxResult returnOrder(Norder po,Double return_percent,Integer return_point) {
		return service.returnOrder(po,return_percent,return_point);
	}

	@RequiresPermissions("manage:norder:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
	
	@RequiresPermissions("manage:norder:pay")
	@RequestMapping("pay/{id}")
	@ResponseBody
	public AjaxResult pay(@PathVariable String id) {
		Integer  result=service.topay(id);
		return new AjaxResult(result+"");
	}
	
	//订单详情点击去结业
	@RequiresPermissions("manage:norder:update")
	@RequestMapping("tofinish/{ut_id}")
	@ResponseBody
	public Map<String,Object> tofinish(@PathVariable String ut_id) {
		Map<String ,Object> map=new HashMap<String,Object >();
		UserTicket ut=uticketService.queryById(ut_id);
		String student_id="";
		List<Appoint> list=appointService.queryWapCh(ut.getNorder_id());
		if(list.size()>0){
			student_id=list.get(0).getStudent_id();
		}
		Student student=new Student();
		student.setUser_id(ut.getUser_id());
		List<Student> slist=studentService.queryByAll(student);
		map.put("students", slist);
		Lesson lesson=lessonService.queryById(ut.getConn_id());
		map.put("lesson", lesson);
		map.put("userticket",ut);
		map.put("student_id", student_id);
		return map;
	}
	//点击结业，执行操作
	@RequiresPermissions("manage:norder:update")
	@RequestMapping("finish/{ut_id}/{student_id}/{left_review}")
	@ResponseBody
	public AjaxResult finish(@PathVariable String ut_id,@PathVariable String student_id,@PathVariable Integer left_review) {
		return service.finishUserTicket(ut_id, student_id, left_review);
	}
	
	
	
	
}
