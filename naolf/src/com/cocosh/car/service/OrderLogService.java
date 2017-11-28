package com.cocosh.car.service;
 
import java.util.List;

import com.cocosh.car.model.OrderLog;

public interface OrderLogService {
	boolean add(OrderLog po);
	List<OrderLog> query(String id);
}
