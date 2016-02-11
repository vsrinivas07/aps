package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.Role;

public class RoleHelperBase {

	public static final String SEQ_KEY = "AS_ROLE_SEQ"; 

	public static final String INSERT_ROLE_SQL = " INSERT INTO AS_ROLE (ROLE_NAME ,ROLE_DESC ,ROLE_ID ) VALUES ( ? ,? ,?)"; 

	public static final String UPDATE_ROLE_SQL = " UPDATE AS_ROLE SET ROLE_NAME = ?  , ROLE_DESC = ?  WHERE ROLE_ID =  ? "; 

	public static final String DELETE_ROLE_SQL = " DELETE FROM  AS_ROLE WHERE ROLE_ID =  ? "; 

	public Role getRole(ResultSet rs) throws SQLException{
		Role role = new Role();

		role.setRoleName(rs.getString("ROLE_NAME"));

		role.setRoleDesc(rs.getString("ROLE_DESC"));

		role.setRoleId(rs.getLong("ROLE_ID"));

		return role;

	}

	public void setRole(PreparedStatement psmt, Role role) throws SQLException{
		if( null == role.getRoleName()){ 
			psmt.setNull(1,Types.VARCHAR); 
		} else { 
			psmt.setString(1,role.getRoleName());
		}

		if( null == role.getRoleDesc()){ 
			psmt.setNull(2,Types.VARCHAR); 
		} else { 
			psmt.setString(2,role.getRoleDesc());
		}

		if( null == role.getRoleId()){ 
			psmt.setNull(3,Types.INTEGER); 
		} else { 
			psmt.setLong(3,role.getRoleId());
		}


	}

}