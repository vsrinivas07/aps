package com.apceps.service.cg.impl;

import com.apceps.domain.Role;
import com.apceps.dao.cg.RoleDaoBase;
import com.apceps.dao.cg.impl.RoleDaoBaseImpl;
import com.apceps.service.cg.RoleServiceBase;
import com.apceps.domain.Role;
import java.util.List;

public class RoleServiceBaseImpl implements RoleServiceBase {

	private RoleDaoBase roleDaoBase = null; 

	public RoleDaoBase getRoleDaoBase() {
		return roleDaoBase;
	}

	public void setRoleDaoBase(RoleDaoBase roleDaoBase) {
		this.roleDaoBase = roleDaoBase;
	}

	public RoleServiceBaseImpl() {
		this.roleDaoBase=new RoleDaoBaseImpl();
	}

	public Role loadRoleByRoleId(Long roleId) throws Exception{
		return roleDaoBase.loadRoleByRoleId(roleId);
	}

	public void deleteRoleByRoleId(Long roleId) throws Exception{
		roleDaoBase.deleteRoleByRoleId(roleId);
	}

	public Role loadRoleByRoleName(String roleName) throws Exception{
		return roleDaoBase.loadRoleByRoleName(roleName);
	}

	public void deleteRoleByRoleName(String roleName) throws Exception{
		roleDaoBase.deleteRoleByRoleName(roleName);
	}

	public List<Role> loadAllRoles() throws Exception{
		return roleDaoBase.loadAllRoles();
	}

	public Long addRole(Role role) throws Exception{
		return roleDaoBase.addRole(role);
	}

	public void updateRole(Role role) throws Exception{
		roleDaoBase.updateRole(role);
	}

}