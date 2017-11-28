package com.cocosh.member.mapper;

import java.util.List;
import java.util.Map;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.member.model.Firm;
import com.cocosh.member.model.Member;

public interface MemberMapper {
	Integer add(Member po);
	Integer del(Map<String,Object> map);
	Integer update(Member po);
	Integer updateOldCard(String mobile);
	List<Member> queryPage(BaseConditionVO vo);
	Member queryById(String id);
	Member queryByIdRefund(String id);
	List<Member> queryByIds(String[] ids);
	Member queryByMobile(String mobile);
	Integer queryCountByMobile(String mobile);
	Member queryByCredential(String credential);
	Integer updateAccount(Member po);
	Integer updateDeposit(Member po);
	List<Firm> queryFirmFromMem(BaseConditionVO vo);
	Integer addExcelImport(List<Member> list);
	List<Member> queryAll();
	List<Member> queryExport(BaseConditionVO vo);
}
