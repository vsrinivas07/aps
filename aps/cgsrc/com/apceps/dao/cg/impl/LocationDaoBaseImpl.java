package com.apceps.dao.cg.impl;

import com.apceps.domain.Location;
import com.apceps.domain.cg.LocationHelperBase;
import com.apceps.dao.cg.LocationDaoBase;
import com.apceps.domain.LocationHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class LocationDaoBaseImpl implements LocationDaoBase {

	private LocationHelper locationHelper = null; 

	public LocationDaoBaseImpl() {
		this.locationHelper=new LocationHelper();
	}

	public LocationHelper getLocationHelper() {
		return locationHelper;
	}

	public void setLocationHelper(LocationHelper locationHelper) {
		this.locationHelper = locationHelper;
	}

	public Location loadLocationByLocationId(Long locationId) throws Exception{
		String sql = " SELECT * FROM AS_LOCATION WHERE LOCATION_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Location location = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,locationId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			location = locationHelper.getLocation(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return location;
	}

	public void deleteLocationByLocationId(Long locationId) throws Exception{
		String sql = LocationHelper.DELETE_LOCATION_SQL;
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

	public List<Location> loadAllLocations() throws Exception{
		String sql = " SELECT * FROM AS_LOCATION " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Location> locationList = new ArrayList<Location>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			locationList.add(locationHelper.getLocation(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return locationList;
	}

	public Long addLocation(Location location) throws Exception{
		String sql = LocationHelper.INSERT_LOCATION_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			location.setLocationId(DBUtil.getId(LocationHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			locationHelper.setLocation(pstmt,location);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return location.getLocationId();
	}

	public void updateLocation(Location location) throws Exception{
		String sql = LocationHelper.UPDATE_LOCATION_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			locationHelper.setLocation(pstmt,location);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}