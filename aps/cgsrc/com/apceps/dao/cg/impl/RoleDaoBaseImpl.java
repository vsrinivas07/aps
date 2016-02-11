package com.apceps.dao.cg.impl;

import com.apceps.domain.Role;
import com.apceps.domain.cg.RoleHelperBase;
import com.apceps.dao.cg.RoleDaoBase;
import com.apceps.domain.RoleHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class RoleDaoBaseImpl implements RoleDaoBase {

	private RoleHelper roleHelper = null; 

	public RoleDaoBaseImpl() {
		this.roleHelper=new RoleHelper();
	}

	public RoleHelper getRoleHelper() {
		return roleHelper;
	}

	public void setRoleHelper(RoleHelper roleHelper) {
		this.roleHelper = roleHelper;
	}

	public Role loadRoleByRoleId(Long roleId) throws Exception{
		String sql = " SELECT * FROM AS_ROLE WHERE ROLE_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Role role = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,roleId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			role = roleHelper.getRole(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return role;
	}

	public void deleteRoleByRoleId(Long roleId) throws Exception{
		String sql = RoleHelper.DELETE_ROLE_SQL;
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

	public Role loadRoleByRoleName(String roleName) throws Exception{
		String sql = " SELECT * FROM AS_ROLE WHERE ROLE_NAME = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Role role = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,roleName);
			rs=pstmt.executeQuery();

			while(rs.next()){
			role = roleHelper.getRole(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return role;
	}

	public void deleteRoleByRoleName(String roleName) throws Exception{
		String sql = RoleHelper.DELETE_ROLE_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,roleName);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Role> loadAllRoles() throws Exception{
		String sql = " SELECT * FROM AS_ROLE " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Role> roleList = new ArrayList<Role>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			roleList.add(roleHelper.getRole(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return roleList;
	}

	public Long addRole(Role role) throws Exception{
		String sql = RoleHelper.INSERT_ROLE_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			role.setRoleId(DBUtil.getId(RoleHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			roleHelper.setRole(pstmt,role);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return role.getRoleId();
	}

	public void updateRole(Role role) throws Exception{
		String sql = RoleHelper.UPDATE_ROLE_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			roleHelper.setRole(pstmt,role);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}