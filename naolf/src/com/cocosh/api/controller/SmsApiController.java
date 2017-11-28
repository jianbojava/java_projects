package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpSender;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.model.Version;
import com.cocosh.sys.service.DictService;
import com.cocosh.sys.service.VersionService;

@Controller
@RequestMapping("api/sms")
public class SmsApiController extends BaseController{
	@Autowired
	private VersionService versionService;
	@Autowired
	private DictService dictService;

	@RequestMapping()
	@ResponseBody
	public AjaxResult smsSend(HttpServletRequest request) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String mobile=request.getParameter("mobile");
		String timeStamp=request.getParameter("timeStamp");
		String summary=request.getParameter("summary");
		String summaryKey = CommonUtil.SECRET_KEY + timeStamp;
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(summary, summaryMD5)){
			String exp="^[1][0-9]{10}$";
			if (!Pattern.matches(exp,mobile)) {
				return new AjaxResult(CommonUtil.ERROR_CODE,"手机号格式不正确",dataMap);
			}
			String code = StringUtil.getRandom(6);
			StringBuffer content = new StringBuffer();
			content.append("您的短信验证码为：");
			content.append(code);
			content.append("，为了您的账户安全，请勿向任何人提供此验证码，感谢您使用智行家服务！");
			String result=HttpSender.send(mobile, content.toString());
			if (result!=null&&result.equals("0")) {
				System.out.println(code);
				writeSession(mobile, code);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CODE,"发送失败，请稍后再试",dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	@RequestMapping("version")
	@ResponseBody
	public AjaxResult version(HttpServletRequest request){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String[] version=request.getParameter("version").split("\\.");
		String type=request.getParameter("type");
		String timeStamp=request.getParameter("timeStamp");
		String summary=request.getParameter("summary");
		String summaryKey = CommonUtil.SECRET_KEY + timeStamp;
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(summary, summaryMD5)){
			dataMap.put("h5_url",dictService.queryByCode("EVCARD_H5_URL"));
			Version newVersion=versionService.queryLast(type);
			if (newVersion!=null) {
				String[] ver=newVersion.getVersion().split("\\.");
				if (Integer.parseInt(ver[0])>Integer.parseInt(version[0])||
						(Integer.parseInt(ver[0])>=Integer.parseInt(version[0])&&Integer.parseInt(ver[1])>Integer.parseInt(version[1]))||
						(Integer.parseInt(ver[0])>=Integer.parseInt(version[0])&&Integer.parseInt(ver[1])>=Integer.parseInt(version[1])&&Integer.parseInt(ver[2])>Integer.parseInt(version[2]))) {
					dataMap.put("must",newVersion.getMust());
					dataMap.put("url",newVersion.getUrl());
					return new AjaxResult(CommonUtil.SUCCESS_CODE,"可更新",dataMap);
				}
				return new AjaxResult(CommonUtil.ERROR_CODE,"已是最新版本",dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CODE,"已是最新版本",dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	
	@RequestMapping("agreement")
	public String agreement(Model model){
		model.addAttribute("agreement",dictService.queryByCode("REGISTER_AGREEMENT"));
		return "app/agreement";
	}
	
}
