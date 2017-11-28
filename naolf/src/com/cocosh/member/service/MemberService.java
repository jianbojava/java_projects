package com.cocosh.member.service;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cocosh.framework.base.AjaxResult;
import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.member.model.Firm;
import com.cocosh.member.model.Member;

public interface MemberService {
	boolean add(Member po);
	boolean addNoLog(Member po);
	boolean review(Integer flg,String ids);
	boolean enabled(Integer flg,String ids);
	boolean updCredential(String credential,String id);
	boolean del(String ids);
	boolean update(Member po);
	boolean updateNoLog(Member po);
	boolean recharge(Member po);
	Page<Member> queryPage(BaseConditionVO vo);
	Member queryById(String id);
	Member queryByMobile(String mobile);
	Member queryByCredential(String credential);
	List<Firm> queryFirmFromMem(BaseConditionVO vo);
	//企业会员导入
	AjaxResult memImport(String suffix,HttpServletRequest req);
	
	List<Member> queryAll();
	//同步Evcard会员
	AjaxResult syncEvcard(String ids);
	//解绑Evcard会员
	AjaxResult removeEvcard(String ids);
	//检查Evcard会员
	AjaxResult checkEvcard(String ids);
	//换卡
	AjaxResult changeEvcard(String ids);
	//用户冻结
	void freezeMember(Member m);
	//用户解冻
	void unfreezeMember(Member m);
	//用户导出
	List<Member> queryExport(BaseConditionVO vo);
}
