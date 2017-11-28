package ${clazz.packagename}.service;
 
import ${basePackage}.framework.base.BaseConditionVO;
import ${basePackage}.framework.mybatis.Page;
import ${clazz.packagename}.model.${clazz.classname};

public interface ${clazz.classname}Service {
	boolean add(${clazz.classname} po);
	boolean del(String ids);
	boolean update(${clazz.classname} po);
	Page<${clazz.classname}> queryPage(BaseConditionVO vo);
	${clazz.classname} queryById(String id);
}
