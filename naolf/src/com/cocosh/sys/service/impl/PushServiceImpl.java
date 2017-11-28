package com.cocosh.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cocosh.framework.jpush.JPushUtil;
import com.cocosh.framework.util.StringUtil;
import com.cocosh.sys.mapper.PushMapper;
import com.cocosh.sys.model.Push;
import com.cocosh.sys.service.PushService;
@Transactional
@Service
public class PushServiceImpl implements PushService {
	@Autowired
	private PushMapper mapper;
	
	@Override
	public List<Push> query(Push push) {
		Integer total=mapper.queryCount(push);
		return mapper.query(push);
	}

	@Override
	public boolean add(Push push) {
		int platform=push.getPlatform();
		int application=push.getApplication();
		String content=push.getContent();
		switch (platform) {
		case 0:
			push.setId(StringUtil.getUuid());
			mapper.add(push);
			//推送
			JPushUtil.buildPushObject_all_all(application, content);
			break;
		case 1:
			push.setId(StringUtil.getUuid());
			mapper.add(push);
			//推送
			JPushUtil.buildPushObject_ios(application, content);
			break;
		case 2:
			push.setId(StringUtil.getUuid());
			mapper.add(push);
			//推送
			JPushUtil.buildPushObject_android(application, content);
			break;
		case 3:
			List<String> ms=push.getUsers();
			for(String m:ms){
				push.setId(StringUtil.getUuid());
				push.setAlias(m);
				mapper.add(push);
			}
			//推送
			JPushUtil.buildPushObject_all_alias(application,(String[])ms.toArray(new String[ms.size()]),content);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(","))>0;
	}

	@Override
	public List<Push> queryByMid(String mid) {
		return mapper.queryByMid(mid);
	}

	@Override
	public List<String> queryMobile(int application) {
		return mapper.queryMobile(application);
	}

}
