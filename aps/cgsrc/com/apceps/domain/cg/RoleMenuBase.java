package com.apceps.domain.cg;

public class RoleMenuBase {

	private Long rolemenuId = null; 

	private Long roleId = null; 

	private Long menuId = null; 

	private boolean addAllowed = false; 

	private boolean modifyAllowed = false; 

	private boolean viewAllowed = false; 

	private boolean deleteAllowed = false; 

	public Long getRolemenuId() {
		return rolemenuId;
	}

	public void setRolemenuId(Long rolemenuId) {
		this.rolemenuId = rolemenuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public boolean isAddAllowed() {
		return addAllowed;
	}

	public void setAddAllowed(boolean addAllowed) {
		this.addAllowed = addAllowed;
	}

	public boolean isModifyAllowed() {
		return modifyAllowed;
	}

	public void setModifyAllowed(boolean modifyAllowed) {
		this.modifyAllowed = modifyAllowed;
	}

	public boolean isViewAllowed() {
		return viewAllowed;
	}

	public void setViewAllowed(boolean viewAllowed) {
		this.viewAllowed = viewAllowed;
	}

	public boolean isDeleteAllowed() {
		return deleteAllowed;
	}

	public void setDeleteAllowed(boolean deleteAllowed) {
		this.deleteAllowed = deleteAllowed;
	}

}