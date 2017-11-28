package com.cocosh.framework.taglib;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

public class Functions {

	public static String principal(Session session) {
		PrincipalCollection principalCollection = (PrincipalCollection) session
				.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
		if (principalCollection!=null) {
			Object object=principalCollection.getPrimaryPrincipal();
			return (String)object;
		}
		return null;
	}

	public static boolean isForceLogout(Session session) {
		return session.getAttribute("session.force.logout") != null;
	}
}
