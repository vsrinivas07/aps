package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.Location;

public class LocationHelperBase {

	public static final String SEQ_KEY = "AS_LOCATION_SEQ"; 

	public static final String INSERT_LOCATION_SQL = " INSERT INTO AS_LOCATION (LOCATION_NAME ,ADDRESS_LINE1 ,ADDRESS_LINE2 ,CITY ,LOCATION_ID ) VALUES ( ? ,? ,? ,? ,?)"; 

	public static final String UPDATE_LOCATION_SQL = " UPDATE AS_LOCATION SET LOCATION_NAME = ?  , ADDRESS_LINE1 = ?  , ADDRESS_LINE2 = ?  , CITY = ?  WHERE LOCATION_ID =  ? "; 

	public static final String DELETE_LOCATION_SQL = " DELETE FROM  AS_LOCATION WHERE LOCATION_ID =  ? "; 

	public Location getLocation(ResultSet rs) throws SQLException{
		Location location = new Location();

		location.setLocationName(rs.getString("LOCATION_NAME"));

		location.setAddressLine1(rs.getString("ADDRESS_LINE1"));

		location.setAddressLine2(rs.getString("ADDRESS_LINE2"));

		location.setCity(rs.getString("CITY"));

		location.setLocationId(rs.getLong("LOCATION_ID"));

		return location;

	}

	public void setLocation(PreparedStatement psmt, Location location) throws SQLException{
		if( null == location.getLocationName()){ 
			psmt.setNull(1,Types.VARCHAR); 
		} else { 
			psmt.setString(1,location.getLocationName());
		}

		if( null == location.getAddressLine1()){ 
			psmt.setNull(2,Types.VARCHAR); 
		} else { 
			psmt.setString(2,location.getAddressLine1());
		}

		if( null == location.getAddressLine2()){ 
			psmt.setNull(3,Types.VARCHAR); 
		} else { 
			psmt.setString(3,location.getAddressLine2());
		}

		if( null == location.getCity()){ 
			psmt.setNull(4,Types.VARCHAR); 
		} else { 
			psmt.setString(4,location.getCity());
		}

		if( null == location.getLocationId()){ 
			psmt.setNull(5,Types.INTEGER); 
		} else { 
			psmt.setLong(5,location.getLocationId());
		}


	}

}