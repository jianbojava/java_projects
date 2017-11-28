package com.cocosh.wap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseController;
import com.cocosh.member.model.Message;
import com.cocosh.member.service.MessageService;

@Controller
@RequestMapping("wap/message/")
public class MessageWapController extends BaseController {
	/*
	 * 消息通知
	 * */
	@Autowired
	private MessageService messageService;
	@RequestMapping("message")
	public String message(Model model) {
		if(getWapUser()==null){
			return "wap/user/login";
		}
		Message message = new Message();
		message.setMem_id(getWapUser().getId());
		List<Message> list = messageService.queryWapAll(message);
		model.addAttribute("list",list);
		return "wap/message/message";
	}
}
