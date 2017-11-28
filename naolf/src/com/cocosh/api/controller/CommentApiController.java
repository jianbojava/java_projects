package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.model.Comment;
import com.cocosh.car.service.CommentService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;

@Controller
@RequestMapping("api/comment")
public class CommentApiController extends BaseController {
	Map<String, Object> dataMap = null;

	@Autowired
	private CommentService service;
	@Autowired
	private MemberService memberService;

	@RequestMapping()
	@ResponseBody
	public AjaxResult list(BaseConditionVO vo) {
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Map<String,Object> totalAvg=service.queryTotalAvg(vo.getId());
			dataMap.put("total",totalAvg.get("total"));
			dataMap.put("avg", totalAvg.get("avg"));
			List<Comment> comments = service.queryPageApp(vo,0);
			String[] excludes = { "modify_date","enabled","car_id","mem_id","mem_mobile","car_name","car_number","station_id","station_name"};
			dataMap.put("comments",JSONArray.fromObject(comments,CommonUtil.createExcludes(excludes)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG, dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testList() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/comment";
		String id="c8d4d311fe88493a876f91bc7257aa01",pageNo="1",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&pageNo="+pageNo+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	@RequestMapping("stationList")
	@ResponseBody
	public AjaxResult stationList(BaseConditionVO vo) {
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			List<Comment> comments = service.queryPageApp(vo,1);
			String[] excludes = { "modify_date","enabled","car_id","mem_id","mem_mobile","car_name","car_number"};
			dataMap.put("comments",JSONArray.fromObject(comments,CommonUtil.createExcludes(excludes)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG, dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testStationList() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/comment/stationList";
		String id="3501010001",pageNo="1",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&pageNo="+pageNo+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(Comment po,BaseConditionVO vo) {
		dataMap = new HashMap<String, Object>();
		String[] securityArray = { "credential","timeStamp" };
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if (CommonUtil.dataValidate(vo.getSummary(), summaryMD5)) {
			Member member=memberService.queryByCredential(vo.getCredential());
			if (member!=null) {
				po.setMem_id(member.getId());
				service.add(po);
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE, CommonUtil.ERROR_PARAMS, dataMap);
	}
	
	public static void testAdd() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/comment/add";
		String car_id="c8d4d311fe88493a876f91bc7257aa01",star="3",content="xxx",credential="v714TnBlvgVPO9FXxBW9X2TLDQ506IDWgqAalmdpxXtjteiPDiICUetam60yhlCphmQCtqu2VOugnVqcCtL1YYahnLLNsnIKXN2sR7tCVlQGbD8jOINRspgn8oeQl5juknTLentDhX45BfKduZhNbwqmweF9BXi5RaS8ErMiTKR8E9U6t7nDP4G3QS1T4toNrFUXNKeDp9FRBopTSBBUx",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "car_id="+car_id+"&star="+star+"&content="+content+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	public static void main(String[] args) throws Exception {
//		testList();
//		testStationList();
		testAdd();
	}
}
