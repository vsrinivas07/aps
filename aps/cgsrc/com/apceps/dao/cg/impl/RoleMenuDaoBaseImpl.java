package com.apceps.dao.cg.impl;

import com.apceps.domain.RoleMenu;
import com.apceps.domain.cg.RoleMenuHelperBase;
import com.apceps.dao.cg.RoleMenuDaoBase;
import com.apceps.domain.RoleMenuHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class RoleMenuDaoBaseImpl implements RoleMenuDaoBase {

	private RoleMenuHelper roleMenuHelper = null; 

	public RoleMenuDaoBaseImpl() {
		this.roleMenuHelper=new RoleMenuHelper();
	}

	public RoleMenuHelper getRoleMenuHelper() {
		return roleMenuHelper;
	}

	public void setRoleMenuHelper(RoleMenuHelper roleMenuHelper) {
		this.roleMenuHelper = roleMenuHelper;
	}

	public RoleMenu loadRoleMenuByRolemenuId(Long rolemenuId) throws Exception{
		String sql = " SELECT * FROM AS_ROLE_MENU WHERE ROLEMENU_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		RoleMenu roleMenu = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,rolemenuId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			roleMenu = roleMenuHelper.getRoleMenu(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return roleMenu;
	}

	public void deleteRoleMenuByRolemenuId(Long rolemenuId) throws Exception{
		String sql = RoleMenuHelper.DELETE_ROLEMENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,rolemenuId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<RoleMenu> loadRoleMenuByRoleId(Long roleId) throws Exception{
		String sql = " SELECT * FROM AS_ROLE_MENU WHERE ROLE_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,roleId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			roleMenuList.add(roleMenuHelper.getRoleMenu(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return roleMenuList;
	}

	public void deleteRoleMenuByRoleId(Long roleId) throws Exception{
		String sql = RoleMenuHelper.DELETE_ROLEMENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,roleId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<RoleMenu> loadRoleMenuByMenuId(Long menuId) throws Exception{
		String sql = " SELECT * FROM AS_ROLE_MENU WHERE MENU_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,menuId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			roleMenuList.add(roleMenuHelper.getRoleMenu(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return roleMenuList;
	}

	public void deleteRoleMenuByMenuId(Long menuId) throws Exception{
		String sql = RoleMenuHelper.DELETE_ROLEMENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,menuId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<RoleMenu> loadAllRoleMenus() throws Exception{
		String sql = " SELECT * FROM AS_ROLE_MENU " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			roleMenuList.add(roleMenuHelper.getRoleMenu(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return roleMenuList;
	}

	public Long addRoleMenu(RoleMenu roleMenu) throws Exception{
		String sql = RoleMenuHelper.INSERT_ROLEMENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			roleMenu.setRolemenuId(DBUtil.getId(RoleMenuHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			roleMenuHelper.setRoleMenu(pstmt,roleMenu);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return roleMenu.getRolemenuId();
	}

	public void updateRoleMenu(RoleMenu roleMenu) throws Exception{
		String sql = RoleMenuHelper.UPDATE_ROLEMENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			roleMenuHelper.setRoleMenu(pstmt,roleMenu);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}