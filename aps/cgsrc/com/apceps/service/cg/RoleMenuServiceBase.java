package com.apceps.service.cg;

import com.apceps.domain.RoleMenu;
import com.apceps.domain.cg.RoleMenuBase;
import java.util.List;

public interface RoleMenuServiceBase {

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