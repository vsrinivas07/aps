package com.apceps.domain;

import java.util.ArrayList;
import java.util.List;

public class UserCdo {

	private User user = null;
 
	private Role role = null ; 
	
	private List<Menu> menuList = new ArrayList<Menu>();

	private List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
	
	

	public UserCdo(User user,Role role, List<Menu> menuList, List<RoleMenu> roleMenuList) {
		super();
		setUser(user); 
		setRole(role);
		setMenuList(menuList);
		setRoleMenuList(roleMenuList); 
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<RoleMenu> getRoleMenuList() {
		return roleMenuList;
	}

	public void setRoleMenuList(List<RoleMenu> roleMenuList) {
		this.roleMenuList = roleMenuList;
	}

	@Override
	public String toString() {
		return "UserCdo [user=" + user + ", role=" + role + ", menuList="
				+ menuList + ", roleMenuList=" + roleMenuList + "]";
	}

 
	
	
}
