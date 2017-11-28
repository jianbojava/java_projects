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

import com.cocosh.car.model.CouponRecord;
import com.cocosh.car.service.CouponRecordService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;

@Controller
@RequestMapping("api/coupon")
public class CouponApiController {
	Map<String, Object> dataMap=null;
	@Autowired
	private CouponRecordService recordService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("allValid")
	@ResponseBody
	public AjaxResult allValid(CouponRecord po,BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"credential","timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Member logM=memberService.queryByCredential(vo.getCredential());
			if (logM!=null) {
				po.setMem_id(logM.getId());
				List<CouponRecord> coupons=recordService.queryAllValid(po);
				String [] excludes = {"id","create_date","enabled","get_date","mem_id","modify_date","order_id","used"};
				dataMap.put("coupons",JSONArray.fromObject(coupons,CommonUtil.createExcludes(excludes)));
				return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
			}
			return new AjaxResult(CommonUtil.ERROR_CREDENTIAL,CommonUtil.CREDENTIAL_INVALID,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testAllValid() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/coupon/allValid";
		String full_amount="60",credential="35HImsiJF0DadYIjbaqK7KyPXTLb2xNyCMg5G8XZe1LN7dc5C73i2Nox8u8C8KFtafewJgKRxhJr5c6gOn0hDI3YeTIUUtqeLaj4xwBctOpNtAJOl0kXPB3fDXdWY4hwvYoinCtuoME0TVQykIany8eXqdI3UKp1GHeFGDLKCHqLG933f0IfxyJS1c4Z0YnIVo4LCLw5GXBiWvf3TBPh2BhrPSgJ1XtWIlZ0oDPnTpr",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +credential+timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "full_amount="+full_amount+"&credential="+credential+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	public static void main(String[] args) throws Exception {
		testAllValid();
	}
	
}
