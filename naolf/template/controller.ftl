package ${clazz.packagename}.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${basePackage}.framework.base.AjaxResult;
import ${basePackage}.framework.base.BaseConditionVO;
import ${basePackage}.framework.base.BaseController;
import ${basePackage}.framework.mybatis.Page;
import ${clazz.packagename}.model.${clazz.classname};
import ${clazz.packagename}.service.${clazz.classname}Service;

@Controller
@RequestMapping("manage/${module}")
public class ${clazz.classname}Controller extends BaseController {
	@Autowired
	private ${clazz.classname}Service service;

	@RequiresPermissions("manage:${module}:list")
	@RequestMapping("list")
	public String list(BaseConditionVO vo, Model model) {
		Page<${clazz.classname}> page=service.queryPage(vo);
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		return "${module}/list";
	}
	
	@RequiresPermissions("manage:${module}:add")
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		return "${module}/add";
	}

	@RequiresPermissions("manage:${module}:add")
	@RequestMapping("add")
	@ResponseBody
	public AjaxResult add(${clazz.classname} po) {
		if (service.add(po)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}

	@RequiresPermissions("manage:${module}:update")
	@RequestMapping("update/{id}")
	public String update(@PathVariable String id,Model model) {
		model.addAttribute("${module}",service.queryById(id));
		return "${module}/update";
	}

	@RequiresPermissions("manage:${module}:update")
	@RequestMapping("update")
	@ResponseBody
	public AjaxResult update(${clazz.classname} po) {
		if (service.update(po)) {
			return new AjaxResult("0");
		} else {
			return new AjaxResult("1");
		}
	}

	@RequiresPermissions("manage:${module}:del")
	@RequestMapping("del/{id}")
	@ResponseBody
	public AjaxResult del(@PathVariable String id) {
		if (service.del(id)) {
			return new AjaxResult("0");
		}
		return new AjaxResult("1");
	}
}
