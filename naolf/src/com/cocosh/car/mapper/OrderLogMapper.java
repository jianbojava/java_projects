package com.cocosh.car.mapper;

import java.util.List;

import com.cocosh.car.model.OrderLog;

public interface OrderLogMapper {
	Integer add(OrderLog po);
	List<OrderLog> query(String id);
}
