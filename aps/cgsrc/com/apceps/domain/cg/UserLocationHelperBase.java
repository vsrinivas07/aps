package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.UserLocation;

public class UserLocationHelperBase {

	public static final String SEQ_KEY = "AS_USER_LOCATION_SEQ"; 

	public static final String INSERT_USERLOCATION_SQL = " INSERT INTO AS_USER_LOCATION (USER_ID ,LOCATION_ID ,LOCATION_NAME ,USERLOC_ID ) VALUES ( ? ,? ,? ,?)"; 

	public static final String UPDATE_USERLOCATION_SQL = " UPDATE AS_USER_LOCATION SET USER_ID = ?  , LOCATION_ID = ?  , LOCATION_NAME = ?  WHERE USERLOC_ID =  ? "; 

	public static final String DELETE_USERLOCATION_SQL = " DELETE FROM  AS_USER_LOCATION WHERE USERLOC_ID =  ? "; 

	public UserLocation getUserLocation(ResultSet rs) throws SQLException{
		UserLocation userLocation = new UserLocation();

		userLocation.setUserId(rs.getLong("USER_ID"));

		userLocation.setLocationId(rs.getLong("LOCATION_ID"));

		userLocation.setLocationName(rs.getString("LOCATION_NAME"));

		userLocation.setUserlocId(rs.getLong("USERLOC_ID"));

		return userLocation;

	}

	public void setUserLocation(PreparedStatement psmt, UserLocation userLocation) throws SQLException{
		if( null == userLocation.getUserId()){ 
			psmt.setNull(1,Types.INTEGER); 
		} else { 
			psmt.setLong(1,userLocation.getUserId());
		}

		if( null == userLocation.getLocationId()){ 
			psmt.setNull(2,Types.INTEGER); 
		} else { 
			psmt.setLong(2,userLocation.getLocationId());
		}

		if( null == userLocation.getLocationName()){ 
			psmt.setNull(3,Types.VARCHAR); 
		} else { 
			psmt.setString(3,userLocation.getLocationName());
		}

		if( null == userLocation.getUserlocId()){ 
			psmt.setNull(4,Types.INTEGER); 
		} else { 
			psmt.setLong(4,userLocation.getUserlocId());
		}


	}

}