package com.cocosh.sys.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;

@Controller
@RequestMapping("manage/sessions")
public class SessionController {
	@Autowired
	private SessionDAO sessionDAO;
	
	@RequiresPermissions("manage:sessions:list")
	@RequestMapping("list")
    public String list(Model model,HttpServletRequest request) {
        Collection<Session> sessions =  sessionDAO.getActiveSessions();
        model.addAttribute("sessions", sessions);
        model.addAttribute("sessionCount", sessions.size());
        model.addAttribute("cur",request.getSession().getId());
        return "sys/sessions/list";
    }

	@RequiresPermissions("manage:sessions:forceLogout")
    @RequestMapping("forceLogout/{sessionId}")
    @ResponseBody
    public AjaxResult forceLogout(@PathVariable String sessionId) {
    	Session session = sessionDAO.readSession(sessionId);
        if(session != null) {
            session.setAttribute("session.force.logout", Boolean.TRUE);
        }
        return new AjaxResult("0");
    }
}
