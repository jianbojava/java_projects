package com.cocosh.nlf.service.impl;

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
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.MessageMapper;
import com.cocosh.member.model.Message;
import com.cocosh.nlf.mapper.AppointMapper;
import com.cocosh.nlf.mapper.CourseMapper;
import com.cocosh.nlf.mapper.LessonMapper;
import com.cocosh.nlf.mapper.UserTicketMapper;
import com.cocosh.nlf.model.Appoint;
import com.cocosh.nlf.model.Course;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.model.UserTicket;
import com.cocosh.nlf.service.CourseService;
import com.cocosh.sys.mapper.DepartMapper;
import com.cocosh.sys.model.Depart;
import com.cocosh.sys.model.User;

@Transactional
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseMapper mapper;
	@Autowired
	private DepartMapper departmapper;
	@Autowired
	private AppointMapper appointMapper;
	@Autowired
	private UserTicketMapper uticketMapper;
	@Autowired
	private LessonMapper lessonMapper;
	@Autowired
	private MessageMapper messageMapper;

	@LogClass(module = "课程发布管理", method = "添加")
	@Override
	public boolean add(Course po) {
		po.setId(StringUtil.getUuid());
		if(po.getType()==1)po.setEnd_time(null);
		return mapper.add(po) > 0;
	}
	
	@LogClass(module = "课程发布管理", method = "操作")
	@Override
	public boolean del(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_field", "enabled");
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
	
	@LogClass(module = "课程发布管理", method = "操作")
	@Override
	public boolean update(Integer flg,String ids) {
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_field", "status");
		map.put("del_flg", flg);
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}

	@LogClass(module = "课程发布管理", method = "操作")
	@Override
	public boolean statusUpdate(Integer flg,String id) {
		Course course=new Course();
		course.setId(id);
		course.setStatus(flg);
		Integer result=mapper.update(course);
		if(result>0){
			if(flg==2){//停止预约，把待审核的全部改为已审核
				Course c=mapper.queryById(id);
				Appoint a=new Appoint();
				a.setCourse_id(id);
				a.setStatus(0);
				String appoint_ids=appointMapper.queryIds(a);//查询出全部待审核的预约
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("del_field", "status");
				if(!StringUtil.isEmpty(appoint_ids)){
					map.put("del_flg", 1);
					map.put("del_ids", appoint_ids.split(","));
					appointMapper.del(map);//预约变成已审核
					String utids=appointMapper.queryUtIds(appoint_ids.split(","));//查询userticketid集合(需要的ids);
					if(c.getType()==1&&(!StringUtil.isEmpty(utids))){//集中课程变成未训练，进阶不需要操作；
						uticketMapper.updateToNoStudy(utids.split(","));//修改userticket状态变成未训练
					}
					//添加消息0415bybobo
					String user_ids[]=appointMapper.queryUserIds(appoint_ids.split(",")).split(",");
					for(String user_id:user_ids){
						Message message=new Message(user_id, "预约确认","您预约的课程"+c.getL_name()+"已确认，请准时参加课程" );
						messageMapper.add(message);
					}
				}
				
			}
			if(flg==3){//该课程结业
				Course c=mapper.queryById(id);
				Appoint a=new  Appoint();
				a.setCourse_id(id);
				a.setStatus(3);
				String appoint_ids=appointMapper.queryIds(a);//结业的appoint_ids;
				a.setStatus(-1);
				String no_appoint_ids=appointMapper.queryIds(a);//需要取消的appoint_ids(包含已经取消的);
				a.setStatus(-2);
				String no_appoint_ids_nocancel=appointMapper.queryIds(a);//已经取消的appoint_ids(不包含已取消的);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("del_field", "status");
				if(!StringUtil.isEmpty(appoint_ids)){
					map.put("del_flg", 4);
					map.put("del_ids", appoint_ids.split(","));
					appointMapper.del(map);//预约变成完成
					//添加消息0417bybobo
					String user_ids[]=appointMapper.queryUserIds(appoint_ids.split(",")).split(",");
					String content="您参加的课程"+c.getL_name()+"已结业，请尽快完成评价";
					String title="结业";
					if(c.getType()==0){
						content="您参加的复训课程"+c.getL_name()+"已结课";
						title="复训结束";
					}
					for(String user_id:user_ids){
						Message message=new Message(user_id, title, content);
						messageMapper.add(message);
					}
					//添加消息完成
					
				}
				if(!StringUtil.isEmpty(no_appoint_ids)){
					map.put("del_flg", 2);
					map.put("del_ids", no_appoint_ids.split(","));
					appointMapper.del(map);//其他预约变成已取消
					//添加消息0417bybobo
					if(!StringUtil.isEmpty(no_appoint_ids_nocancel)){
						String user_ids[]=appointMapper.queryUserIds(no_appoint_ids_nocancel.split(",")).split(",");
						for(String user_id:user_ids){
							Message message=new Message(user_id, "预约取消", "您预约的课程"+c.getL_name()+"已取消，请重新预约");
							messageMapper.add(message);
						}
					}
					
				}
				String utids="";
				String no_utids="";
				if(!StringUtil.isEmpty(appoint_ids)){
					utids=appointMapper.queryUtIds(appoint_ids.split(","));//查询userticketid集合(需要计算提成的ids);
				}
				if(c.getType()==1){//集中课程结业要计算提成；
					if(!StringUtil.isEmpty(no_appoint_ids)){
					  no_utids=appointMapper.queryUtIds(no_appoint_ids.split(","));//查询未训练的userticketid,集合;
					}
					if(!StringUtil.isEmpty(no_utids)) uticketMapper.updateToCancel(no_utids.split(","));//修改userticket状态变成已取消
					if(!StringUtil.isEmpty(utids)) uticketMapper.updateToEnd(utids.split(","));//修改userticket状态变成已结业
					/*计算提成代码*/
					
				}else{//进阶课程不需要计算提成
					if(!StringUtil.isEmpty(utids)){
						Lesson lesson=lessonMapper.queryById(c.getLesson_id());
						Appoint qappoint=new Appoint();
						UserTicket ut=new UserTicket();
						//如果复训中同一个user_ticket_id的appoint都等于lesson的train，那么userticket结束
						for (String utid : utids.split(",")) {
							qappoint.setUser_ticket_id(utid);
							qappoint.setCourse_type(0);
							qappoint.setStatus(4);
							Integer qcount=appointMapper.queryCount(qappoint);
							if(lesson.getTrain()<=qcount){//复训次数等于lesson的train，userticket结束
								ut.setId(utid);
								ut.setStatus(6);
								uticketMapper.update(ut);
							}
				       }
					}
			    }
		   }
		}
		return true;
	}

	@LogClass(module = "课程发布管理", method = "修改")
	@Override
	public boolean update(Course po) {
		if(po.getType()==1) po.setEnd_time(null);
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Course> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");    
		vo.setOperat_id(user.getId());
		mapper.queryPage(vo);
		Page<Course> page=PaginationInterceptor.endPage();
		List<Course> list=page.getResult();
		Appoint appoint = new Appoint();
		appoint.setStatus(100);
		for(Course course:list){
			appoint.setCourse_id(course.getId());
			course.setHave_appoint(appointMapper.queryCount(appoint));//已预约数量
		}
		return page;
	}


	@Override
	public List<Depart> queryByBId(String parent_id) {
		return departmapper.queryChild(parent_id);
	}

	@Override
	public Course queryById(String id) {
		// TODO Auto-generated method stub
		return mapper.queryById(id);
	}

	@Override
	public List<Course> queryWapList(Course po,String user_ticket_id) {
		List<Course> list=mapper.queryWapList(po);
		for(Course c:list){
			Appoint appoint = new Appoint();
			appoint.setStatus(100);
			appoint.setCourse_id(c.getId());
			c.setHave_appoint(appointMapper.queryCount(appoint));//已预约数量
			appoint.setUser_ticket_id(user_ticket_id);
			Integer  is_appoint=appointMapper.queryCount(appoint);//判断是否已经预约
			Integer students=0;
			if(is_appoint>0) students=1;//已预约;
			c.setStudents(students);
		}
		return list;
	}

	@Override
	public Course queryWapclick(String id) {
		Course course = new Course();
		course.setId(id);
		List<Course> list=mapper.queryWapList(course);
		for(Course c:list){
			Appoint appoint = new Appoint();
			appoint.setStatus(10);
			appoint.setCourse_id(c.getId());
			c.setHave_appoint(appointMapper.queryCount(appoint));//已预约数量
			c.setL_name(lessonMapper.queryById(c.getLesson_id()).getName());//发布的课程关联的课程名称
		}
		return list.get(0);
	}

	
}
