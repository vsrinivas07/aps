package com.apceps.dao.cg;

import com.apceps.domain.RoleMenu;
import java.util.List;

public interface RoleMenuDaoBase {

	public RoleMenu loadRoleMenuByRolemenuId(Long rolemenuId) throws Exception;

	public void deleteRoleMenuByRolemenuId(Long rolemenuId) throws Exception;

	public List<RoleMenu> loadRoleMenuByRoleId(Long roleId) throws Exception;

	public void deleteRoleMenuByRoleId(Long roleId) throws Exception;

	public List<RoleMenu> loadRoleMenuByMenuId(Long menuId) throws Exception;

	public void deleteRoleMenuByMenuId(Long menuId) throws Exception;

	public List<RoleMenu> loadAllRoleMenus() throws Exception;

	public Long addRoleMenu(RoleMenu roleMenu) throws Exception;

	public void updateRoleMenu(RoleMenu roleMenu) throws Exception;

}