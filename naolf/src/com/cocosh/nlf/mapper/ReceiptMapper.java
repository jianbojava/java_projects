package com.cocosh.nlf.mapper;

import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.nlf.model.Receipt;

public interface ReceiptMapper {
	Integer add(Receipt po);
	Integer del(String[] ids);
	Integer update(Receipt po);
	List<Receipt> queryPage(BaseConditionVO vo);
	Receipt queryById(String id);
	List<Receipt> queryByOrderId(String norder_id);
}
