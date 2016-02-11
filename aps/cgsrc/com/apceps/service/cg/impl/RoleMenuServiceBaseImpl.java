package com.apceps.service.cg.impl;

import com.apceps.domain.RoleMenu;
import com.apceps.dao.cg.RoleMenuDaoBase;
import com.apceps.dao.cg.impl.RoleMenuDaoBaseImpl;
import com.apceps.service.cg.RoleMenuServiceBase;
import com.apceps.domain.RoleMenu;
import java.util.List;

public class RoleMenuServiceBaseImpl implements RoleMenuServiceBase {

	private RoleMenuDaoBase roleMenuDaoBase = null; 

	public RoleMenuDaoBase getRoleMenuDaoBase() {
		return roleMenuDaoBase;
	}

	public void setRoleMenuDaoBase(RoleMenuDaoBase roleMenuDaoBase) {
		this.roleMenuDaoBase = roleMenuDaoBase;
	}

	public RoleMenuServiceBaseImpl() {
		this.roleMenuDaoBase=new RoleMenuDaoBaseImpl();
	}

	public RoleMenu loadRoleMenuByRolemenuId(Long rolemenuId) throws Exception{
		return roleMenuDaoBase.loadRoleMenuByRolemenuId(rolemenuId);
	}

	public void deleteRoleMenuByRolemenuId(Long rolemenuId) throws Exception{
		roleMenuDaoBase.deleteRoleMenuByRolemenuId(rolemenuId);
	}

	public List<RoleMenu> loadRoleMenuByRoleId(Long roleId) throws Exception{
		return roleMenuDaoBase.loadRoleMenuByRoleId(roleId);
	}

	public void deleteRoleMenuByRoleId(Long roleId) throws Exception{
		roleMenuDaoBase.deleteRoleMenuByRoleId(roleId);
	}

	public List<RoleMenu> loadRoleMenuByMenuId(Long menuId) throws Exception{
		return roleMenuDaoBase.loadRoleMenuByMenuId(menuId);
	}

	public void deleteRoleMenuByMenuId(Long menuId) throws Exception{
		roleMenuDaoBase.deleteRoleMenuByMenuId(menuId);
	}

	public List<RoleMenu> loadAllRoleMenus() throws Exception{
		return roleMenuDaoBase.loadAllRoleMenus();
	}

	public Long addRoleMenu(RoleMenu roleMenu) throws Exception{
		return roleMenuDaoBase.addRoleMenu(roleMenu);
	}

	public void updateRoleMenu(RoleMenu roleMenu) throws Exception{
		roleMenuDaoBase.updateRoleMenu(roleMenu);
	}

}