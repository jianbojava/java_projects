package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.EmpDisJournal;

public interface EmpDisJournalMapper {
	List<EmpDisJournal> queryPage(BaseConditionVO vo);
	Integer add(EmpDisJournal po);
	List<EmpDisJournal> queryListAll(EmpDisJournal empDisJournal);
	List<EmpDisJournal> queryDetails(EmpDisJournal po);
}
