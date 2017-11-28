package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Upgrade;

public interface UpgradeMapper {
	Integer add(Upgrade po);
	Integer del(String[] ids);
	Integer update(Upgrade po);
	List<Upgrade> queryPage(BaseConditionVO vo);
	Upgrade queryById(String id);
	Upgrade queryByutid123(String user_ticket_id3);
}
