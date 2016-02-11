package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.User;

public class UserHelperBase {

	public static final String SEQ_KEY = "AS_USER_SEQ"; 

	public static final String INSERT_USER_SQL = " INSERT INTO AS_USER (USER_NAME ,USER_PASSWORD ,ROLE_ID ,EMAIL ,USER_ID ) VALUES ( ? ,? ,? ,? ,?)"; 

	public static final String UPDATE_USER_SQL = " UPDATE AS_USER SET USER_NAME = ?  , USER_PASSWORD = ?  , ROLE_ID = ?  , EMAIL = ?  WHERE USER_ID =  ? "; 

	public static final String DELETE_USER_SQL = " DELETE FROM  AS_USER WHERE USER_ID =  ? "; 

	public User getUser(ResultSet rs) throws SQLException{
		User user = new User();

		user.setUserName(rs.getString("USER_NAME"));

		user.setUserPassword(rs.getString("USER_PASSWORD"));

		user.setRoleId(rs.getLong("ROLE_ID"));

		user.setEmail(rs.getString("EMAIL"));

		user.setUserId(rs.getLong("USER_ID"));

		return user;

	}

	public void setUser(PreparedStatement psmt, User user) throws SQLException{
		if( null == user.getUserName()){ 
			psmt.setNull(1,Types.VARCHAR); 
		} else { 
			psmt.setString(1,user.getUserName());
		}

		if( null == user.getUserPassword()){ 
			psmt.setNull(2,Types.VARCHAR); 
		} else { 
			psmt.setString(2,user.getUserPassword());
		}

		if( null == user.getRoleId()){ 
			psmt.setNull(3,Types.INTEGER); 
		} else { 
			psmt.setLong(3,user.getRoleId());
		}

		if( null == user.getEmail()){ 
			psmt.setNull(4,Types.VARCHAR); 
		} else { 
			psmt.setString(4,user.getEmail());
		}

		if( null == user.getUserId()){ 
			psmt.setNull(5,Types.INTEGER); 
		} else { 
			psmt.setLong(5,user.getUserId());
		}


	}

}