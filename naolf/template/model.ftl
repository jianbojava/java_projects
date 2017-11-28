package ${clazz.packagename}.model;
${import!}
<#list clazz.imports as being>
import ${being};
</#list>

import java.io.Serializable;

public class ${clazz.classname} implements Serializable {
 <#list attributes as being>
 	private ${being.type} ${being.name};
 </#list>
 <#list attributes as being>
 	
 	public ${being.type} get${being.name?cap_first}() {
 		return ${being.name};
 	}
 	
 	public void set${being.name?cap_first}(${being.type} ${being.name}) {
 		this.${being.name} = ${being.name};
 	}
 </#list>
}