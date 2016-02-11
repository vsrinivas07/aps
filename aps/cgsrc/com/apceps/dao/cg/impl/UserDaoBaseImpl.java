package com.apceps.dao.cg.impl;

import com.apceps.domain.User;
import com.apceps.domain.cg.UserHelperBase;
import com.apceps.dao.cg.UserDaoBase;
import com.apceps.domain.UserHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class UserDaoBaseImpl implements UserDaoBase {

	private UserHelper userHelper = null; 

	public UserDaoBaseImpl() {
		this.userHelper=new UserHelper();
	}

	public UserHelper getUserHelper() {
		return userHelper;
	}

	public void setUserHelper(UserHelper userHelper) {
		this.userHelper = userHelper;
	}

	public User loadUserByUserId(Long userId) throws Exception{
		String sql = " SELECT * FROM AS_USER WHERE USER_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		User user = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,userId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			user = userHelper.getUser(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return user;
	}

	public void deleteUserByUserId(Long userId) throws Exception{
		String sql = UserHelper.DELETE_USER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,userId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public User loadUserByUserName(String userName) throws Exception{
		String sql = " SELECT * FROM AS_USER WHERE USER_NAME = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		User user = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,userName);
			rs=pstmt.executeQuery();

			while(rs.next()){
			user = userHelper.getUser(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return user;
	}

	public void deleteUserByUserName(String userName) throws Exception{
		String sql = UserHelper.DELETE_USER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,userName);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<User> loadUserByRoleId(Long roleId) throws Exception{
		String sql = " SELECT * FROM AS_USER WHERE ROLE_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<User> userList = new ArrayList<User>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,roleId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			userList.add(userHelper.getUser(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return userList;
	}

	public void deleteUserByRoleId(Long roleId) throws Exception{
		String sql = UserHelper.DELETE_USER_SQL;
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

	public List<User> loadAllUsers() throws Exception{
		String sql = " SELECT * FROM AS_USER " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<User> userList = new ArrayList<User>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			userList.add(userHelper.getUser(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return userList;
	}

	public Long addUser(User user) throws Exception{
		String sql = UserHelper.INSERT_USER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			user.setUserId(DBUtil.getId(UserHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			userHelper.setUser(pstmt,user);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return user.getUserId();
	}

	public void updateUser(User user) throws Exception{
		String sql = UserHelper.UPDATE_USER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			userHelper.setUser(pstmt,user);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}