package com.apceps.service.cg;

import com.apceps.domain.Role;
import com.apceps.domain.cg.RoleBase;
import java.util.List;

public interface RoleServiceBase {

	public Role loadRoleByRoleId(Long roleId) throws Exception;

	public void deleteRoleByRoleId(Long roleId) throws Exception;

	public Role loadRoleByRoleName(String roleName) throws Exception;

	public void deleteRoleByRoleName(String roleName) throws Exception;

	public List<Role> loadAllRoles() throws Exception;

	public Long addRole(Role role) throws Exception;

	public void updateRole(Role role) throws Exception;

}