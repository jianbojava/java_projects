package ${clazz.packagename}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<#if module??>
import ${basePackage}.framework.annotation.LogClass;
</#if>	

import ${basePackage}.framework.base.BaseConditionVO;
import ${basePackage}.framework.mybatis.Page;
import ${basePackage}.framework.mybatis.PaginationInterceptor;
import ${basePackage}.framework.util.StringUtil;
import ${clazz.packagename}.mapper.${clazz.classname}Mapper;
import ${clazz.packagename}.model.${clazz.classname};
import ${clazz.packagename}.service.${clazz.classname}Service;

@Transactional
@Service
public class ${clazz.classname}ServiceImpl implements ${clazz.classname}Service {
	@Autowired
	private ${clazz.classname}Mapper mapper;

<#if module??>
	@LogClass(module = "${module}管理", method = "添加")
</#if>	
	@Override
	public boolean add(${clazz.classname} po) {
		po.setId(StringUtil.getUuid());
		return mapper.add(po) > 0;
	}
	
<#if module??>
    @LogClass(module = "${module}管理", method = "删除")
</#if>
	@Override
	public boolean del(String ids) {
		return mapper.del(ids.split(",")) > 0;
	}

<#if module??>
	@LogClass(module = "${module}管理", method = "修改")
</#if>
	@Override
	public boolean update(${clazz.classname} po) {
		return mapper.update(po) > 0;
	}

	@Override
	public Page<${clazz.classname}> queryPage(BaseConditionVO vo) {
		PaginationInterceptor.startPage(vo.getPageNo(),vo.getPageSize());
		mapper.queryPage(vo);
		Page<${clazz.classname}> page=PaginationInterceptor.endPage();
		return page;
	}

	@Override
	public ${clazz.classname} queryById(String id) {
		return mapper.queryById(id);
	}
	
}
