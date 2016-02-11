package com.apceps.dao.cg.impl;

import com.apceps.domain.UserLocation;
import com.apceps.domain.cg.UserLocationHelperBase;
import com.apceps.dao.cg.UserLocationDaoBase;
import com.apceps.domain.UserLocationHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class UserLocationDaoBaseImpl implements UserLocationDaoBase {

	private UserLocationHelper userLocationHelper = null; 

	public UserLocationDaoBaseImpl() {
		this.userLocationHelper=new UserLocationHelper();
	}

	public UserLocationHelper getUserLocationHelper() {
		return userLocationHelper;
	}

	public void setUserLocationHelper(UserLocationHelper userLocationHelper) {
		this.userLocationHelper = userLocationHelper;
	}

	public UserLocation loadUserLocationByUserlocId(Long userlocId) throws Exception{
		String sql = " SELECT * FROM AS_USER_LOCATION WHERE USERLOC_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		UserLocation userLocation = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,userlocId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			userLocation = userLocationHelper.getUserLocation(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return userLocation;
	}

	public void deleteUserLocationByUserlocId(Long userlocId) throws Exception{
		String sql = UserLocationHelper.DELETE_USERLOCATION_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,userlocId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<UserLocation> loadUserLocationByUserId(Long userId) throws Exception{
		String sql = " SELECT * FROM AS_USER_LOCATION WHERE USER_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<UserLocation> userLocationList = new ArrayList<UserLocation>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,userId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			userLocationList.add(userLocationHelper.getUserLocation(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return userLocationList;
	}

	public void deleteUserLocationByUserId(Long userId) throws Exception{
		String sql = UserLocationHelper.DELETE_USERLOCATION_SQL;
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

	public List<UserLocation> loadUserLocationByLocationId(Long locationId) throws Exception{
		String sql = " SELECT * FROM AS_USER_LOCATION WHERE LOCATION_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<UserLocation> userLocationList = new ArrayList<UserLocation>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,locationId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			userLocationList.add(userLocationHelper.getUserLocation(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return userLocationList;
	}

	public void deleteUserLocationByLocationId(Long locationId) throws Exception{
		String sql = UserLocationHelper.DELETE_USERLOCATION_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,locationId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<UserLocation> loadAllUserLocations() throws Exception{
		String sql = " SELECT * FROM AS_USER_LOCATION " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<UserLocation> userLocationList = new ArrayList<UserLocation>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			userLocationList.add(userLocationHelper.getUserLocation(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return userLocationList;
	}

	public Long addUserLocation(UserLocation userLocation) throws Exception{
		String sql = UserLocationHelper.INSERT_USERLOCATION_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			userLocation.setUserlocId(DBUtil.getId(UserLocationHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			userLocationHelper.setUserLocation(pstmt,userLocation);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return userLocation.getUserlocId();
	}

	public void updateUserLocation(UserLocation userLocation) throws Exception{
		String sql = UserLocationHelper.UPDATE_USERLOCATION_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			userLocationHelper.setUserLocation(pstmt,userLocation);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}