package com.cocosh.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cocosh.car.model.Car;
import com.cocosh.car.model.Dot;
import com.cocosh.car.service.BrandService;
import com.cocosh.car.service.CarService;
import com.cocosh.car.service.DotService;
import com.cocosh.car.service.ParkingService;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.base.BaseController;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.service.DictService;

@Controller
@RequestMapping("api/car")
public class CarApiController extends BaseController{
	Map<String, Object> dataMap=null;
	@Autowired
	private CarService service;
	@Autowired
	private DotService dotService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ParkingService parkingService;
	@Autowired
	private DictService dictService;
	
	@RequestMapping()
	@ResponseBody
	public AjaxResult list(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			List<Car> cars=service.queryPageApp(vo);
			String [] excludes = {"number","type","nature","engine_num","register_addr","register_num","register_date","product_area","fuel_distance","fuel_type","tank_vol","cargo_vol","brand_id","brand_name","model_id","model_name","exfactory_date","charge_length",
					"sup_company","sup_person","sup_mobile","sign_date","end_date","sort","dot_id","dot_name","park_id","park_name","review","review_error","enabled","insurance","create_date","modify_date","body_weight","body_size","top_speed","total_distance"};
			dataMap.put("cars",JSONArray.fromObject(cars,CommonUtil.createExcludes(excludes)));
			if (vo.getPageNo()==1&&StringUtil.isEmpty(vo.getBrand_id())) {
				String [] brand_excludes = {"logo","url","brief","sort","types","type_names","enabled","type","create_date","modify_date"};
				dataMap.put("brands",JSONArray.fromObject(brandService.queryAllByType("0"),CommonUtil.createExcludes(brand_excludes)));
			}
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testCar() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/car";
		String pageNo="1",orderField="price",orderDirection="desc",brand_id="",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "pageNo="+pageNo+"&orderField="+orderField+"&orderDirection="+orderDirection+"&brand_id="+brand_id+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public AjaxResult detail(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			Car car=service.queryByIdAPP(vo);
			String [] excludes = {"number","type","nature","engine_num","register_addr","register_num","register_date","product_area","fuel_distance","fuel_type","tank_vol","cargo_vol","brand_id","brand_name","model_id","model_name","charge_length",
					"sup_company","sup_person","sup_mobile","sign_date","end_date","sort","dot_id","dot_name","park_id","park_name","review","review_error","enabled","create_date","modify_date","body_weight","body_size","top_speed","comment_star","dispatch_name"};
			dataMap.put("car",JSONObject.fromObject(car,CommonUtil.createExcludes(excludes)));
			vo.setPark_id(car.getPark_id());
			String[] park_excludes={"create_date","dispatch_id","dispatch_name","dot_id","dot_name","enabled","sign_date","end_date","latitude","longitude","modify_date","name","number","price","mkt_price"};
			dataMap.put("park",JSONObject.fromObject(parkingService.queryDistanceByVo(vo), CommonUtil.createExcludes(park_excludes)));
			dataMap.put("insurance",Double.parseDouble(dictService.queryByCode("INSURANCE_AMOUNT")));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testDetail() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/car/detail";
		String id="1b2d121ccb4d4749ae46de9b3abe89ef",longitude="117.9145110000",latitude="33.4793620000",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "id="+id+"&longitude="+longitude+"&latitude="+latitude+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	@RequestMapping("dot")
	@ResponseBody
	public AjaxResult dot(BaseConditionVO vo){
		dataMap = new HashMap<String, Object>();
		String [] securityArray = {"timeStamp"};
		String summaryKey = CommonUtil.SECRET_KEY + CommonUtil.reflectMethod(vo, securityArray);
		String summaryMD5 = DigestUtils.md5Hex(summaryKey);
		if(CommonUtil.dataValidate(vo.getSummary(), summaryMD5)){
			List<Dot> dots=dotService.queryPageApp(vo);
			String [] excludes = {"gallerys","province_id","province_name","city_id","city_name","region_id","region_name","tel","enabled","number","start_time","end_time","create_date","modify_date"};
			dataMap.put("dots",JSONArray.fromObject(dots,CommonUtil.createExcludes(excludes)));
			return new AjaxResult(CommonUtil.SUCCESS_CODE,CommonUtil.SUCCESS_MSG,dataMap);
		}
		return new AjaxResult(CommonUtil.ERROR_CODE,CommonUtil.ERROR_PARAMS,dataMap);
	}
	
	public static void testDot() throws Exception{
		String requestUrl =CommonUtil.SERVERDOMAIN+"/api/car/dot";
		String longitude="117.9145110000",latitude="33.4793620000",timeStamp = StringUtil.getTimeStamp();
		String summaryKey = CommonUtil.SECRET_KEY +timeStamp;
		String summary=DigestUtils.md5Hex(summaryKey);
		String resultStr = HttpUtil.connectURL(requestUrl, "longitude="+longitude+"&latitude="+latitude+"&timeStamp="+timeStamp+"&summary="+summary);
		System.out.println(resultStr);
	}
	
	public static void main(String[] args) throws Exception {
//		testCar();
		testDetail();
//		testDot();
	}
}
