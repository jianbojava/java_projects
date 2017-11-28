package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.EmpDisJournal;

public interface EmpDisJournalService {
	Page<EmpDisJournal> queryPage(BaseConditionVO vo);
	List<EmpDisJournal> queryList(EmpDisJournal po);
	List<EmpDisJournal> queryDetails(EmpDisJournal po);
}
