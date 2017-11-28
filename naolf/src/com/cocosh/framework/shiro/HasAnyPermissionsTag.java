package com.cocosh.framework.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.PermissionTag;

/**
 * 自定义标签（hasAnyPermissions）
 * 
 * @author jerry
 */
public class HasAnyPermissionsTag extends PermissionTag {
	private static final long serialVersionUID = 1L;
	private static final String PERMISSION_NAMES_DELIMETER = ",";

	@Override
	protected boolean showTagBody(String permissionNames) {
		boolean hasAnyPermission = false;
		Subject subject = getSubject();
		if (subject != null) {
			//遍历权限集合检查是否含有其中任何一个权限
			for (String permission : permissionNames.split(PERMISSION_NAMES_DELIMETER)) {
				if (subject.isPermitted(permission.trim())) {
					hasAnyPermission = true;
					break;
				}
			}
		}
		return hasAnyPermission;
	}
}