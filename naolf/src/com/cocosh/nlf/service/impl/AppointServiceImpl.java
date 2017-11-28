package com.cocosh.nlf.service.impl;

import java.io.IOException;
import java.util.Date;
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
import com.cocosh.member.mapper.MessageMapper;
import com.cocosh.member.model.Message;
import com.cocosh.nlf.mapper.AppointMapper;
import com.cocosh.nlf.mapper.CourseMapper;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.NorderMapper;
import com.cocosh.nlf.mapper.StudentMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Course;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.Norder;
import com.cocosh.nlf.model.Student;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.AppointService;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class AppointServiceImpl implements AppointService {
	@Autowired
	private AppointMapper mapper;
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private UserTicketMapper uticketMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private NorderMapper norderMapper;

	@LogClass(module = "课程预约管理", method = "添加")
	@Override
	public boolean add(Appoint po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	//添加预约
	@Override
	public AjaxResult addWap(Appoint po) {
		String student_id=po.getStudent_id();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("wapuser");
		Course course = courseMapper.queryById(po.getCourse_id());
		if(course==null||course.getStatus()!=1) {
			return new AjaxResult(CommonUtil.ERROR_CODE,"课程已结束",null);
		}
		if(course.getStart_time().getTime()<=new Date().getTime()){
			return new AjaxResult(CommonUtil.ERROR_CODE,"上课时间不能小于当前时间",null);
		}
		UserTicket userTicket = uticketMapper.queryById(po.getUser_ticket_id());
		if(userTicket==null) return new AjaxResult(CommonUtil.ERROR_CODE,"订单不存在",null); 
		if(course.getType()==1){//集中课程预约
			//查看是否已经预约
			String course_id=po.getCourse_id();
			po.setStatus(100);
			po.setCourse_id(null);
			Integer count=mapper.queryCount(po);
			if(count>0)  return new AjaxResult(CommonUtil.ERROR_CODE,"该课程不能再次预约",null);
			po.setStudent_id(null);
			po.setUser_ticket_id(null);
			po.setCourse_id(course_id);
			//查看该发布的课程是否已经预约人满
			Integer reserve_count=mapper.queryCount(po);
			if(course.getNum()==null||course.getNum()<=reserve_count){
				return new AjaxResult(CommonUtil.ERROR_CODE,"预约人数已满",null);
			}
			//判断userticket的状态
			if(userTicket.getStatus()!=0&&userTicket.getStatus()!=2){
				return new AjaxResult(CommonUtil.ERROR_CODE,"状态错误,不能预约",null);
			}
			//添加预约
			po.setStudent_id(student_id);
			po.setUser_ticket_id(userTicket.getId());
			po.setId(StringUtil.getUuid());
			po.setUser_id(user.getId());
			po.setLesson_id(course.getLesson_id());
			po.setStatus(0);
			po.setCourse_type(course.getType());
			po.setNorder_id(userTicket.getNorder_id());
			po.setEnabled("0");
			Integer result=mapper.add(po);
			if(result>0){
				userTicket.setStatus(1);
				Integer upresult=uticketMapper.update(userTicket);
				if(upresult>0)  return new AjaxResult(CommonUtil.SUCCESS_CODE,"预约成功",null); 
			}
			return new AjaxResult(CommonUtil.ERROR_CODE,"预约失败",null); 
		}else{//复训的预约
			if(userTicket.getStatus()!=4&&userTicket.getStatus()!=5){
				return new AjaxResult(CommonUtil.ERROR_CODE,"状态错误,不能预约",null);
			}
			//判断是否已经预约
			po.setStatus(100);
			Integer count=mapper.queryCount(po);
			if(count>0)  return new AjaxResult(CommonUtil.ERROR_CODE,"该课程不能再次预约",null);
			po.setUser_ticket_id(null);
			//查看该发布的课程是否已经预约人满
			Integer reserve_count=mapper.queryCount(po);
			if(course.getNum()==null||course.getNum()<=reserve_count){
				return new AjaxResult(CommonUtil.ERROR_CODE,"预约人数已满",null);
			}
			//判断复训的预约次数
			po.setCourse_id(null);
			po.setCourse_type(0);
			po.setUser_ticket_id(userTicket.getId());
			Integer review_count=mapper.queryCount(po);
			
			//查询lesson，判断train
			Lesson lesson=lessonMapper.queryById(course.getLesson_id());
			if(lesson==null||lesson.getTrain()==null) return new AjaxResult(CommonUtil.ERROR_CODE,"该课程不能预约",null);
			if(lesson.getTrain()<=review_count){
				return new AjaxResult(CommonUtil.ERROR_CODE,"复训预约已满",null);
			}
			po.setCourse_type(1);
			List<Appoint> list=mapper.queryWapCoures(po);
			if(list==null)  return new AjaxResult(CommonUtil.ERROR_CODE,"请先预约集中课程",null);
			//添加预约
			po.setStudent_id(list.get(0).getStudent_id());
			po.setCourse_id(course.getId());
			po.setId(StringUtil.getUuid());
			po.setUser_id(user.getId());
			po.setLesson_id(course.getLesson_id());
			po.setStatus(0);
			po.setCourse_type(course.getType());
			po.setNorder_id(userTicket.getNorder_id());
			po.setEnabled("0");
			Integer result=mapper.add(po);
			if(result>0){
				userTicket.setStatus(5);
				Integer upresult=uticketMapper.update(userTicket);
				if(upresult>0)  return new AjaxResult(CommonUtil.SUCCESS_CODE,"预约成功",null); 
			}
			return new AjaxResult(CommonUtil.ERROR_CODE,"预约失败",null); 
		}
	}
    //取消预约
	@Override
	public AjaxResult updateWap(Appoint po) {
		po.setStatus(100);
		List<Appoint> list=mapper.queryWapCoures(po);
		if(list.size()<=0){//根据userticket查询与此课程绑定的已预约的预约列表
			return new AjaxResult(CommonUtil.ERROR_CODE,"取消失败",null); 
		}
	    po=list.get(0);
	    if(po.getStatus()!=0) return new AjaxResult(CommonUtil.ERROR_CODE,"预约已生效,不能取消",null); 
		po.setStatus(2);
		if(mapper.update(po)>0){//课程的取消预约
			if(po.getCourse_type()==0){//复训的取消预约
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"取消成功",null); 
			}
			//集中课程的取消预约（修改）
			UserTicket userTicket = new UserTicket();
			userTicket.setId(po.getUser_ticket_id());
			userTicket.setStatus(2);
			Integer result=uticketMapper.update(userTicket);
			if(result>0) return new AjaxResult(CommonUtil.SUCCESS_CODE,"取消成功",null); 
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"取消失败",null); 
	}
	
	@LogClass(module = "课程预约管理", method = "修改")
	@Override
	public boolean update(Appoint po) {
		Integer result=mapper.update(po);
		if(result>0&&po.getStatus()==2){//预约取消
			Appoint  p=mapper.queryById(po.getId());
			if(p.getCourse_type()==1){//集中,(ut变成已取消),进阶不变
				UserTicket ut=uticketMapper.queryById(p.getUser_ticket_id());
				if(ut.getStatus()<=3){//后台，即使是上课中也可以取消
					ut.setStatus(2);
					uticketMapper.update(ut);
				}
			}
			//添加消息
			String lesson_name="";
			Lesson lesson=lessonMapper.queryById(p.getLesson_id());
			if(lesson!=null) lesson_name=lesson.getName();
			Message message=new Message(p.getUser_id(), "预约取消","您预约的课程"+lesson_name+"已取消，请重新预约" );
			messageMapper.add(message);
		}
		if(result>0&&po.getStatus()==1){//预约审核通过
			Appoint  p=mapper.queryById(po.getId());
			if(p.getCourse_type()==1){//集中,(ut变成未训练),进阶不变
				UserTicket ut=uticketMapper.queryById(p.getUser_ticket_id());
				if(ut.getStatus()<=2){
					ut.setStatus(-3);
					uticketMapper.update(ut);
				}
			}
			//添加消息
			String lesson_name="";
			Lesson lesson=lessonMapper.queryById(p.getLesson_id());
			if(lesson!=null) lesson_name=lesson.getName();
			Message message=new Message(p.getUser_id(), "预约确认","您预约的课程"+lesson_name+"已确认，请准时参加课程" );
			messageMapper.add(message);
		}
		return result>0;
	}

	
	@Override
	public List<Appoint> queryAllId(String id) {
		return mapper.queryAllId(id);
	}

	@Override
	public Page<Appoint> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Appoint> page=PaginationInterceptor.endPage();
		List<Appoint> list=page.getResult();
		for(Appoint a:list){
			try {
				if(!StringUtil.isEmpty(a.getUser_mobile())){
						a.setUser_mobile(SecurityUtil.decrypt(a.getUser_mobile()));
				}
				if(!StringUtil.isEmpty(a.getRef_mobile())){
					a.setRef_mobile(SecurityUtil.decrypt(a.getRef_mobile()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	@LogClass(module = "课程预约管理", method = "操作")
	@Override
	public boolean statusUpdate(Integer flg,String ids,Integer course_type) {
		String[] idsArray=ids.split(",");
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_field", "status");
		map.put("del_flg", flg);
		map.put("del_ids", idsArray);
		Integer result= mapper.del(map);
		if(result>0){
			String utids=mapper.queryUtIds(idsArray);//查询userticketid,集合;
			if(!StringUtil.isEmpty(utids)){
				Appoint appoint=mapper.queryById(idsArray[0]);
				if(course_type==1){//集中训练，需要计算提成
					uticketMapper.updateToEnd(utids.split(","));//修改userticket状态变成已结业
					/*计算提成代码*/
					
					
				}else{//进阶结业不需要计算提成
					if(appoint!=null){
						Lesson lesson=lessonMapper.queryById(appoint.getLesson_id());
						Appoint qappoint=new Appoint();
						UserTicket ut=new UserTicket();
						//如果复训中同一个user_ticket_id的appoint都等于lesson的train，那么userticket结束
						for (String utid : utids.split(",")) {
							qappoint.setUser_ticket_id(utid);
							qappoint.setCourse_type(0);
							qappoint.setStatus(4);
							Integer qcount=mapper.queryCount(qappoint);
							if(lesson.getTrain()<=qcount){//复训次数等于lesson的train，userticket结束
								ut.setId(utid);
								ut.setStatus(6);
								uticketMapper.update(ut);
							}
						}
					}
				}
				//添加消息0417bybobo
				if(appoint!=null){
					String lesson_name="";
					Lesson lesson=lessonMapper.queryById(appoint.getLesson_id());
					if(lesson!=null) lesson_name=lesson.getName();
					String user_ids[]=mapper.queryUserIds(idsArray).split(",");
					String content="您参加的课程"+lesson_name+"已结业，请尽快完成评价";
					String title="结业";
					if(appoint.getCourse_type()==0){
						content="您参加的复训课程"+lesson_name+"已结课";
						title="复训结束";
					}
					for(String user_id:user_ids){
						Message message=new Message(user_id, title, content);
						messageMapper.add(message);
					}	
				}
			}
		}
		return true;
	}
	@Override
	public boolean del(String ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Appoint queryById(String id) {
		return mapper.queryById(id);
	}
	@Override
	public List<Appoint> queryWapRecord(String user_ticket_id,Integer status) {
		Appoint po=new Appoint();
		po.setUser_ticket_id(user_ticket_id);
		po.setStatus(status);
		return mapper.queryRecord(po);
	}
	@Override
	public Appoint queryWapAll(Appoint po) {
		// TODO Auto-generated method stub
		return mapper.queryWapAll(po);
	}
	@Override
	public List<Appoint> queryWapCoures(Appoint po) {
		return mapper.queryWapCoures(po);
	}
	
	@Override
	public AjaxResult study(Appoint po) {
		po=mapper.queryById(po.getId());//查询到的预约
		if(po==null||(po.getStatus()!=1))  return new AjaxResult(CommonUtil.ERROR_CODE,"预约状态错误，还不能上课",null); 
		UserTicket ut=uticketMapper.queryById(po.getUser_ticket_id());
		if(ut==null||(ut.getStatus()!=-3&&ut.getStatus()!=5)) return new AjaxResult(CommonUtil.ERROR_CODE,"课程状态错误，还不能上课",null); 
		po.setStatus(3);
		po.setStudy_date(new Date());
		Integer result=mapper.update(po);
		if(result>0){
			String lesson_name="";
			Lesson lesson=lessonMapper.queryById(po.getLesson_id());
			if(lesson!=null) lesson_name=lesson.getName();
			if(po.getCourse_type()==0){
				Message message=new Message(po.getUser_id(), "扫码成功", "您于"+StringUtil.getLocalData()+"开始课程"+lesson_name);
				messageMapper.add(message);
				/**扫码后添加信息
				Norder norder=norderMapper.queryById(po.getNorder_id());
				Course course=courseMapper.queryById(po.getCourse_id());
				Student student=studentMapper.queryById(po.getStudent_id());
				String msg="课程编号:"+ut.getSn()+"<br>课程名称:"+lesson_name+"&nbsp;进阶训练<br>课程时间:"+StringUtil.getLocalDataM(course.getStart_time())+"<br>孩子姓名:"+student.getName()+"<br>孩子年龄:"+student.getAge();
				if(norder.getType()==1){//卡券订单
					 msg="课程编号:"+ut.getSn()+"<br>卡券编号:"+norder.getTicket_sn()+"<br>课程名称:"+lesson_name+"&nbsp;进阶训练<br>课程时间"+StringUtil.getLocalDataM(course.getStart_time())+"<br>孩子姓名:"+student.getName()+"<br>孩子年龄:"+student.getAge();
				}
				**/
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"上课成功",po.getId()); //进阶课程
			}
			ut.setStatus(3);//集中课程修改状态
			Integer utresult=uticketMapper.update(ut);
			if(utresult>0) {
				Message message=new Message(po.getUser_id(), "扫码成功", "您于"+StringUtil.getLocalData()+"开始课程"+lesson_name);
				messageMapper.add(message);
				/**扫码后添加信息
				Norder norder=norderMapper.queryById(po.getNorder_id());
				Course course=courseMapper.queryById(po.getCourse_id());
				Student student=studentMapper.queryById(po.getStudent_id());
				String msg="课程编号:"+ut.getSn()+"<br>课程名称:"+lesson_name+"&nbsp;集中训练<br>课程时间:"+StringUtil.getLocalDataM(course.getStart_time())+"<br>孩子姓名:"+student.getName()+"<br>孩子年龄:"+student.getAge();
				if(norder.getType()==1){//卡券订单
					 msg="课程编号:"+ut.getSn()+"<br>卡券编号:"+norder.getTicket_sn()+"<br>课程名称:"+lesson_name+"&nbsp;集中训练<br>课程时间:"+StringUtil.getLocalDataM(course.getStart_time())+"<br>孩子姓名:"+student.getName()+"<br>孩子年龄:"+student.getAge();
				}
				**/
				return new AjaxResult(CommonUtil.SUCCESS_CODE,"上课成功",po.getId()); 
			}
			return new AjaxResult(CommonUtil.ERROR_CODE,"上课失败",null); 
			
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,"上课失败",null);
	}
	@Override
	public List<Appoint> queryWapCh(String n_id) {
		Appoint appoint = new Appoint();
		appoint.setNorder_id(n_id);
		// TODO Auto-generated method stub
		return mapper.queryWapCoures(appoint);
	}
	
	@Override
	public String getStudentIdByNorderId(String n_id) {
		Appoint appoint = new Appoint();
		appoint.setNorder_id(n_id);
		List<Appoint> list= mapper.queryWapCoures(appoint);
		if(list.size()>0) return list.get(0).getStudent_id();
		Norder norder=norderMapper.queryById(n_id);
		if(norder!=null&&norder.getType()==1&&(!StringUtil.isEmpty(norder.getUpnorder()))){
			appoint.setNorder_id(norder.getUpnorder());
			list=mapper.queryWapCoures(appoint);
			if(list.size()>0) return list.get(0).getStudent_id();
		}
		return null;
	}
	
	@Override
	public void addRemind() {
		List<Appoint> list=mapper.queryRemind();
		for(Appoint appoint:list){
			Message message=new Message(appoint.getUser_id(), "上课提醒", "您预约的课程"+appoint.getLesson_name()+"即将开课，开课时间"+StringUtil.getLocalData(appoint.getCourse_date())+"，请准时参加。");
			messageMapper.add(message);
		}
	}
	
}
