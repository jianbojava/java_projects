package com.cocosh.sys.mapper;

import java.util.List;

import com.cocosh.sys.model.Regions;

public interface RegionsMapper {
	List<Regions> queryByPId(String id);
}
