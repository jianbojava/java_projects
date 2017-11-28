package com.cocosh.framework.base;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.util.OssUtil;
import com.cocosh.nlf.model.Lesson;
import com.cocosh.nlf.service.LessonService;
import com.cocosh.sys.model.Regions;
import com.cocosh.sys.model.User;
import com.cocosh.sys.service.RegionsService;

/**
 * Controller基类
 * 
 * @author jerry
 */
@Controller("base")
public class BaseController {
	@Autowired
	private RegionsService regionService;
	@Autowired
	private LessonService lessonService;

	/**
	 * 获取当前登陆的用户
	 * 
	 * @return user
	 */
	public User getUser() {
		Subject sub = SecurityUtils.getSubject();
		Session session = sub.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}
	
	/**
	 * 获取当前wap登陆的用户
	 * 
	 * @return user
	 */
	public User getWapUser() {
		Subject sub = SecurityUtils.getSubject();
		Session session = sub.getSession();
		User user = (User) session.getAttribute("wapuser");
		return user;
	}

	/**
	 * 获取用户的数据权限
	 * 
	 * @param vo
	 */
	public void dataAuth(BaseConditionVO vo) {
		Subject sub = SecurityUtils.getSubject();
		if (sub.isPermitted("personal:data") && !sub.isPermitted("all:data")) {
			vo.setUser_id(getUser().getId());
		}
	}

	/**
	 * 文件上传
	 * 
	 * @return ajaxResult
	 */
	@RequestMapping("upload")
	@ResponseBody
	public AjaxResult upload(HttpServletRequest request) {
		String filePath = OssUtil.uploadFile(request, OssUtil.BUCKET_MANAGE);
		if (filePath != null) {
			return new AjaxResult("0", filePath);
		}
		return new AjaxResult("1", "");
	}

	/**
	 * 向cookie中写数据
	 * 
	 * @param rep
	 * @param cookieName
	 * @param cookie
	 * @param maxAge
	 */
	public void writeCookie(HttpServletRequest req, HttpServletResponse rep,
			String cookieName, String cookie, int maxAge) {
		Cookie c = new Cookie(cookieName, cookie);
		c.setPath("/");
		c.setMaxAge(maxAge);
		rep.addCookie(c);
	}

	/**
	 * 从cookie读取数据
	 * 
	 * @param cookieName
	 * @param req
	 * @return
	 */
	public String readCookie(String cookieName, HttpServletRequest req) {
		String cookie = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals(cookieName)) {
					cookie = c.getValue();
					break;
				}
			}
		}
		return cookie;
	}

	/**
	 * 向session中写数据
	 * 
	 * @param sessionName
	 * @param sessionValue
	 */
	public void writeSession(String sessionName, String sessionValue) {
		Subject sub = SecurityUtils.getSubject();
		sub.getSession().setAttribute(sessionName, sessionValue);
	}

	/**
	 * 从session中读数据
	 * 
	 * @param sessionName
	 * @return
	 */
	public String readSession(String sessionName) {
		Subject sub = SecurityUtils.getSubject();
		Object o = sub.getSession().getAttribute(sessionName);
		return o != null ? (String) o : null;
	}

	/**
	 * 省市区级联查询
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("regions/{pid}")
	@ResponseBody
	public List<Regions> regions(@PathVariable String pid) {
		return regionService.queryByPId(pid);
	}
	/**
	 * 省市区级联查询
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("lesson/listed")
	@ResponseBody
	public List<Lesson> lesson() {
		return lessonService.queryAll();
	}


}
