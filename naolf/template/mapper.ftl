package ${clazz.packagename}.mapper;

import java.util.List;

import ${basePackage}.framework.base.BaseConditionVO;
import ${clazz.packagename}.model.${clazz.classname};

public interface ${clazz.classname}Mapper {
	Integer add(${clazz.classname} po);
	Integer del(String[] ids);
	Integer update(${clazz.classname} po);
	List<${clazz.classname}> queryPage(BaseConditionVO vo);
	${clazz.classname} queryById(String id);
}
