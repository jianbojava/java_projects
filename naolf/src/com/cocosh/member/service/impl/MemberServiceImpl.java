package com.cocosh.member.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cocosh.car.mapper.PaymentLogMapper;
import com.cocosh.car.model.PaymentLog;
import com.cocosh.framework.annotation.LogClass;
import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.excel.ExcelUtil;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.framework.mybatis.PaginationInterceptor;
import com.cocosh.framework.util.CommonUtil;
import com.cocosh.framework.util.HttpUtil;
import com.cocosh.framework.util.SecurityUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.member.mapper.FirmMapper;
import com.cocosh.member.mapper.LevelMapper;
import com.cocosh.member.mapper.MemberMapper;
import com.cocosh.member.model.Firm;
import com.cocosh.member.model.Level;
import com.cocosh.member.model.Member;
import com.cocosh.member.service.MemberService;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;
	@Autowired
	private LevelMapper levelMapper;
	@Autowired
	private FirmMapper firmMapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;
	

	@LogClass(module = "会员管理", method = "添加")
	@Override
	public boolean add(Member po) {
		po.setId(StringUtil.getUuid());
		Level level=levelMapper.queryMin();
		if(level!=null) po.setPoint_lv(level.getId());
		return mapper.add(po) > 0;
	}
	
	@Override
	public boolean addNoLog(Member po) {
		po.setId(StringUtil.getUuid());
		Level level=levelMapper.queryMin();
		if(level!=null) po.setPoint_lv(level.getId());
		return mapper.add(po) > 0;
	}
	
    @LogClass(module = "会员管理", method = "删除")
	@Override
	public boolean del(String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("del_field","del_flg");
		map.put("del_flg", "1");
		map.put("del_ids", ids.split(","));
		return mapper.del(map) > 0;
	}
    @LogClass(module = "会员管理", method = "审核")
   	@Override
   	public boolean review(Integer flg, String ids) {
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("del_field","review");
   		map.put("del_flg", flg);
   		map.put("del_ids", ids.split(","));
   		return mapper.del(map) > 0;
   	}
    
    @LogClass(module = "会员管理", method = "操作")
   	@Override
   	public boolean enabled(Integer flg, String ids) {
   		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("del_field","enabled");
   		map.put("del_flg", flg);
   		map.put("del_ids", ids.split(","));
   		return mapper.del(map) > 0;
   	}
    

	@Override
	public boolean updCredential(String credential, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
   		map.put("del_field","credential");
   		map.put("del_flg", credential);
   		map.put("del_ids", new String[]{id});
   		return mapper.del(map) > 0;
	}

	@LogClass(module = "会员管理", method = "修改")
	@Override
	public boolean update(Member po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<Member> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<Member> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public Member queryById(String id) {
		return mapper.queryById(id);
	}

	@Override
	public Member queryByMobile(String mobile) {
		return mapper.queryByMobile(mobile);
	}

	@Override
	public Member queryByCredential(String credential) {
		return mapper.queryByCredential(credential);
	}

	@Override
	public boolean updateNoLog(Member po) {
		return mapper.update(po) > 0;
	}

	@Override
	public List<Firm> queryFirmFromMem(BaseConditionVO vo) {
		return mapper.queryFirmFromMem(vo);
	}

	@Override
	public AjaxResult memImport(String suffix,HttpServletRequest req) {
		String[] template=new String[]{"编号","手机号","姓名","会员卡号","身份证号","驾驶证号","收件地址","所属企业","备注"};
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
			MultipartFile mFile = multiRequest.getFile("file");
			Workbook wb=suffix.equals("xls")?new HSSFWorkbook(mFile.getInputStream()):new XSSFWorkbook(mFile.getInputStream());
			Sheet sheet = wb.getSheetAt(0);
	        int totalRows = sheet.getPhysicalNumberOfRows();  
	        int totalCells = 0;  
	        if (totalRows >= 1 && sheet.getRow(0) != null) {  
	            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
	        }else {
	        	return new AjaxResult("1","Excel内容不合法，请使用模板上传");
			}
	        //验证ECXEL内容是否规范
	        Row titleRow = sheet.getRow(0);
	        for (int c = 0; c < totalCells; c++) {
				if (ExcelUtil.ConvertCell(titleRow.getCell(c)).equals(template[c])) {
					continue;
				}
				return new AjaxResult("1","Excel内容不合法，请使用模板上传");
			}
	        //读取所有企业并存入Map
	        Set<String> name_sets=new HashSet<String>();
	        for (int r = 1; r < totalRows; r++) {  
	            Row row = sheet.getRow(r);  
	            if (row == null) {  
	                continue;  
	            }
	            //第7列企业名称放入set
	            name_sets.add(ExcelUtil.ConvertCell(row.getCell(7)));
	        }
	        List<Firm> firms=firmMapper.queryFirmByExcel(new ArrayList<String>(name_sets));
	        Map<String, String> firmMap=new HashMap<String, String>();
	        for(Firm f:firms){
	        	firmMap.put(f.getName(),f.getId());
	        }
	        //读取全部记录插入数据库
	        String level_id=null;
	        Level level=levelMapper.queryMin();
			if(level!=null) level_id=level.getId();
	        List<Member> mList=new ArrayList<Member>();
	        List<Member> eList=new ArrayList<Member>();
	        for (int r = 1; r < totalRows; r++) {  
	            Row row = sheet.getRow(r);  
	            if (row == null) {  
	                continue;  
	            }
	            Member m=new Member();
	            m.setId(StringUtil.getUuid());
	            m.setNumber(ExcelUtil.ConvertCell(row.getCell(0)));
	            m.setMobile(ExcelUtil.ConvertCell(row.getCell(1)));
	            m.setName(ExcelUtil.ConvertCell(row.getCell(2)));
	            m.setCard_number(ExcelUtil.ConvertCell(row.getCell(3)));
	            m.setCard_no(ExcelUtil.ConvertCell(row.getCell(4)));
	            m.setDriver_no(ExcelUtil.ConvertCell(row.getCell(5)));
	            m.setShip_addr(ExcelUtil.ConvertCell(row.getCell(6)));
	            m.setFirm_id(firmMap.get(ExcelUtil.ConvertCell(row.getCell(7))));
	            m.setFirm_name(ExcelUtil.ConvertCell(row.getCell(7)));
	            m.setRemark(ExcelUtil.ConvertCell(row.getCell(8)));
	            m.setPoint_lv(level_id);
	            if (mapper.queryCountByMobile(ExcelUtil.ConvertCell(row.getCell(1)))>0) {
					eList.add(m);
				}else{
					mList.add(m);
				}
	        }
	        if (!mList.isEmpty()) {
				mapper.addExcelImport(mList);
			}
	        return new AjaxResult("0","导入完成：成功导入 "+mList.size()+" 条，失败 "+eList.size()+" 条",eList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new AjaxResult("1","Excel内容不合法，请使用模板上传");
	}

	@Override
	public List<Member> queryAll() {
		return mapper.queryAll();
	}

	@Override
	public AjaxResult syncEvcard(String ids) {
		List<Member> mems=mapper.queryByIds(ids.split(","));
		JSONArray arr=new JSONArray();
		for(Member m:mems){
			JSONObject o=new JSONObject();
			o.put("mobileNo", m.getMobile());
			o.put("name",m.getName());
			o.put("memID",m.getCard_no());
			o.put("licenseCopyUrl",m.getDriver_scan());
			o.put("address",m.getShip_addr());
			o.put("memCardID",m.getCard_number());
			arr.add(o);
		}
		JSONObject o=new JSONObject();
		o.put("memberList", arr);
		try {
			String timestamp=String.valueOf(new Date().getTime());
			String sn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
			String result=HttpUtil.connectPost(CommonUtil.SYNC_EVCARD_MEM,"zhixingjia",timestamp,sn,o.toString());
			if (result!=null) {
				String msg="";
				JSONObject r=JSONObject.parseObject(result);
				JSONArray a=r.getJSONArray("resultList");
				for(int i=0;i<a.size();i++){
					JSONObject r1= a.getJSONObject(0);
					if (r1.getInteger("status")==0) {
						//修改old_card_number,换卡使用
						mapper.updateOldCard(r1.getString("mobile"));
						msg+=r1.getString("mobile")+"&nbsp;同步成功<br>";
					}else {
						msg+=r1.getString("mobile")+"&nbsp;"+r1.getString("msg")+"<br>";
					}
				}
				return new AjaxResult("0",msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult("1","系统繁忙，请稍后再试。");
	}

	@Override
	public AjaxResult removeEvcard(String ids) {
		List<Member> mems=mapper.queryByIds(ids.split(","));
		JSONArray arr=new JSONArray();
		for(Member m:mems){
			JSONObject o=new JSONObject();
			o.put("mobileNo", m.getMobile());
			arr.add(o);
		}
		JSONObject o=new JSONObject();
		o.put("memberList", arr);
		try {
			String timestamp=String.valueOf(new Date().getTime());
			String sn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
			String result=HttpUtil.connectPut(CommonUtil.UNBIND_EVCARD_MEM,"zhixingjia",timestamp,sn,o.toString());
			if (result!=null) {
				String msg="";
				JSONObject r=JSONObject.parseObject(result);
				JSONArray a=r.getJSONArray("resultList");
				for(int i=0;i<a.size();i++){
					JSONObject r1= a.getJSONObject(0);
					if (r1.getInteger("status")==0) {
						msg+=r1.getString("mobile")+"&nbsp;解绑成功<br>";
					}else {
						msg+=r1.getString("mobile")+"&nbsp;"+r1.getString("msg")+"<br>";
					}
				}
				return new AjaxResult("0",msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult("1","系统繁忙，请稍后再试。");
	}

	@Override
	public AjaxResult checkEvcard(String ids) {
		List<Member> mems=mapper.queryByIds(ids.split(","));
		try {
			String msg="";
			for(Member mem:mems){
				String timestamp=String.valueOf(new Date().getTime());
				String sn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
				String result=HttpUtil.connectGet(CommonUtil.SYNC_EVCARD_MEM+"?mobilePhone="+mem.getMobile()+"&authId="+mem.getCard_no(),"zhixingjia",timestamp,sn);
				if (result!=null) {
					Member upd=new Member();
					upd.setId(mem.getId());
					upd.setEvcard_status(Integer.parseInt(result));
					mapper.update(upd);
					msg+=mem.getMobile()+"&nbsp;"+evcardStatus(Integer.parseInt(result))+"<br>";
				}else {
					msg+=mem.getMobile()+"&nbsp;检查失败<br>";
				}
			}
			return new AjaxResult("0",msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult("1");
	}
	
	@Override
	public AjaxResult changeEvcard(String ids) {
		List<Member> mems=mapper.queryByIds(ids.split(","));
		JSONArray arr=new JSONArray();
		for(Member m:mems){
			JSONObject o=new JSONObject();
			o.put("authId", m.getCard_no());
			o.put("oldMemCard",m.getOld_card_number());
			o.put("newMemCard",m.getCard_number());
			arr.add(o);
		}
		JSONObject o=new JSONObject();
		o.put("memberList", arr);
		System.out.println(o.toString());
		try {
			String timestamp=String.valueOf(new Date().getTime());
			String sn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
			String result=HttpUtil.connectPut(CommonUtil.CHANGE_MEM_CARD,"zhixingjia",timestamp,sn,o.toString());
			if (result!=null) {
				String msg="";
				JSONObject r=JSONObject.parseObject(result);
				JSONArray a=r.getJSONArray("resultList");
				for(int i=0;i<a.size();i++){
					JSONObject r1= a.getJSONObject(0);
					if (r1.getInteger("status")==0) {
						msg+=r1.getString("authId")+"&nbsp;换卡成功<br>";
					}else {
						msg+=r1.getString("authId")+"&nbsp;"+r1.getString("msg")+"<br>";
					}
				}
				return new AjaxResult("0",msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult("1","系统繁忙，请稍后再试。");
	}

	@Override
	public void freezeMember(Member m) {
		JSONArray arr=new JSONArray();
		JSONObject o1=new JSONObject();
		o1.put("mobileNo",m.getMobile());
		o1.put("memID", m.getCard_no());
		o1.put("memCardID", m.getCard_number());
		arr.add(o1);
		JSONObject o2=new JSONObject();
		o2.put("memberList", arr);
		String timestamp=String.valueOf(new Date().getTime());
		String apisn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
		String result=HttpUtil.connectPut(CommonUtil.FREEZE_MEMBERS,"zhixingjia",timestamp,apisn,o2.toString());
		System.out.println("------------------freeze members-------------"+result);
	}

	@Override
	public void unfreezeMember(Member m) {
		if (m.getReview()==1&&m.getEnabled()==0) {
			JSONArray arr=new JSONArray();
			JSONObject o1=new JSONObject();
			o1.put("mobileNo",m.getMobile());
			o1.put("memID", m.getCard_no());
			o1.put("memCardID", m.getCard_number());
			arr.add(o1);
			JSONObject o2=new JSONObject();
			o2.put("memberList", arr);
			String timestamp=String.valueOf(new Date().getTime());
			String apisn=SecurityUtil.md5("zhixingjia"+"Q!W@e3r4Rgn"+timestamp);
			String result=HttpUtil.connectPut(CommonUtil.UNFREEZE_MEMBERS,"zhixingjia",timestamp,apisn,o2.toString());
			System.out.println("------------------unfreeze members-------------"+result);
		}
	}

	@Override
	public List<Member> queryExport(BaseConditionVO vo) {
		return mapper.queryExport(vo);
	}

	@LogClass(module = "会员管理", method = "充值")
	@Override
	public boolean recharge(Member po) {
		//判断企业余额、保证金
		Member logM=mapper.queryByIdRefund(po.getId());
		//保证金
		if (po.getDeposit()>0) {
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(po.getId(), null, po.getDeposit(), "保证金充值",null, null, 4, 1, 1,po.getRemark()));
		}
		if (po.getDeposit()<0) {
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(po.getId(), null, Math.abs(po.getDeposit()), "保证金退返",null, null, 4, 1, 4,po.getRemark()));
		}
		if (po.getAccount()>0) {
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(po.getId(), null, po.getAccount(), "余额充值",null, null, 4, 1, 2,po.getRemark()));
		}
		if (po.getAccount()<0) {
			//添加支付流水记录
			paymentLogMapper.add(new PaymentLog(po.getId(), null, Math.abs(po.getAccount()), "余额退返",null, null, 4, 1, 5,po.getRemark()));
		}
		//修改余额、保证金等
		po.setDeposit(logM.getDeposit()+po.getDeposit());
		po.setAccount(logM.getAccount()+po.getAccount());
		return mapper.update(po)>0;
	}
	
	private static String evcardStatus(int status){
		switch (status) {
		case 0:
			return "未同步";
		case 1:
			return "已存在";
		case 2:
			return "待审核";
		case 3:
			return "审核不通过";
		default:
			return "用户无效";
		}
	}

}
