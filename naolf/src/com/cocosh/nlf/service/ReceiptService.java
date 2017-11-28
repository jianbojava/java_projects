package com.cocosh.nlf.service;
 
import java.util.List;

import com.cocosh.framework.base.BaseConditionVO;
import com.cocosh.framework.mybatis.Page;
import com.cocosh.nlf.model.Receipt;

public interface ReceiptService {
	boolean add(Receipt po);
	boolean del(String ids);
	boolean update(Receipt po);
	Page<Receipt> queryPage(BaseConditionVO vo);
	Receipt queryById(String id);
	List<Receipt> queryByOrderId(String norder_id);
}
