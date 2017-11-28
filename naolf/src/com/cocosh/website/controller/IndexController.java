package com.cocosh.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.sys.model.News;
import com.cocosh.sys.service.NewsService;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	@Autowired
	private NewsService service;

	@RequestMapping("index")
	public String index() {
		return "website/index";
	}

	@RequestMapping("joinus")
	public String joinus() {
		return "website/joinus";
	}
	
	@RequestMapping("newsList")
	public String newsList(Model model,BaseConditionVO vo) {
		vo.setPageSize(5);
		vo.setEnabled("0");
		Page<News> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "website/news_list";
	}
	
	@RequestMapping("newsDetail/{id}")
	public String newsDetail(@PathVariable String id,Model model) {
		model.addAttribute("detail",service.queryById(id));
		return "website/news_detail";
	}
	
	@RequestMapping("download")
	public String download() {
		return "website/download";
	}
	//在微信端打开
	@RequestMapping("index/browserError")
	public String error() {
		return "wap/error";
	}
}
