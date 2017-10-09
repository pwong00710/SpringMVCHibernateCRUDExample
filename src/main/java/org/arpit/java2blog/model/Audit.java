package org.arpit.java2blog.model;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Audit {
	
	public final static String ACTION_INSERT = "ins";
	public final static String ACTION_UPDATE = "upd";
	public final static String ACTION_DELETE = "del";
	public final static String ACTION_SELECT = "sel";
	
	Integer id;
	String action;
	String name;
	Date auditDate;
	
	public Audit(String action) {
		this.action = action;
		this.name = getUserName();
		this.auditDate = new Date();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	private String getUserName() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String name = auth.getName(); //get logged in username
    	
    	return name;
	}
}
