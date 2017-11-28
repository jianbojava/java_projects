package com.cocosh.car.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.DateUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.car.mapper.IllegalMapper;
import com.cocosh.car.model.Illegal;
import com.cocosh.car.model.IllegalExport;
import com.cocosh.car.service.IllegalService;

@Transactional
@Service
public class IllegalServiceImpl implements IllegalService {
	@Autowired
	private IllegalMapper mapper;

	@Override
	public void add() {
		String timestamp=String.valueOf(new Date().getTime());
		String sn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
		String result=HttpUtil.connectGet(CommonUtil.ILLEGALINFO+"?time="+DateUtil.addDay(-1,new Date(),"yyyyMMdd"),"zhixingjia",timestamp,sn);
		if (result!=null) {
			System.out.println(result);
			JSONObject object=JSON.parseObject(result);
			JSONArray arrays=object.getJSONArray("illegalInfos");
			if (arrays.size()>0) {
				List<Illegal> list=new ArrayList<Illegal>();
				for (int i = 0; i < arrays.size(); i++) {
					JSONObject o=arrays.getJSONObject(i);
					Illegal ill=new Illegal();
					ill.setIllegalSeq(o.getInteger("illegalSeq"));
					ill.setVin(o.getString("vin"));
					ill.setIllegal_date(DateUtil.stringToDate(StringUtil.formatDateString(o.getString("illegalTime")),"yyyy-MM-dd HH:mm:ss"));
					ill.setDocument_number(o.getString("documentNumber"));
					ill.setPlace(o.getString("place"));
					ill.setIllegal_content(o.getString("illegalContent"));
					ill.setIllegal_code(o.getString("illegalCode"));
					ill.setIllegal_amount(o.getInteger("serviceAmount"));
					ill.setPayment(o.getInteger("payment"));
					ill.setPayment_status(o.getInteger("paymentStatus"));
					ill.setIllegal_amount(o.getInteger("illegalAmount"));
					ill.setPenalty_point(o.getInteger("penaltyPoint"));
					ill.setStatus(o.getInteger("status"));
					ill.setOrderSeq(o.getString("orderSeq"));
					ill.setIllegal_imageUrl(o.getString("illegalImageUrl"));
					ill.setCertificate_image(o.getString("certificateImage"));
					ill.setCreate_date(DateUtil.stringToDate(StringUtil.formatDateString(o.getString("createdTime")),"yyyy-MM-dd HH:mm:ss"));
					list.add(ill);
				}
				mapper.add(list);
			}
		}
	}
	
	@Override
	public Page<Illegal> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Illegal> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public boolean update(Illegal po) {
		po.setIds(po.getId().split(","));
		return mapper.update(po)>0;
	}

	@Override
	public List<IllegalExport> queryExport(BaseConditionVO vo) {
		return mapper.queryExport(vo);
	}
	
}
