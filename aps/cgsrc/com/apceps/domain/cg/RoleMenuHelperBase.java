package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.RoleMenu;

public class RoleMenuHelperBase {

	public static final String SEQ_KEY = "AS_ROLE_MENU_SEQ"; 

	public static final String INSERT_ROLEMENU_SQL = " INSERT INTO AS_ROLE_MENU (ROLE_ID ,MENU_ID ,ADD_ALLOWED ,MODIFY_ALLOWED ,VIEW_ALLOWED ,DELETE_ALLOWED ,ROLEMENU_ID ) VALUES ( ? ,? ,? ,? ,? ,? ,?)"; 

	public static final String UPDATE_ROLEMENU_SQL = " UPDATE AS_ROLE_MENU SET ROLE_ID = ?  , MENU_ID = ?  , ADD_ALLOWED = ?  , MODIFY_ALLOWED = ?  , VIEW_ALLOWED = ?  , DELETE_ALLOWED = ?  WHERE ROLEMENU_ID =  ? "; 

	public static final String DELETE_ROLEMENU_SQL = " DELETE FROM  AS_ROLE_MENU WHERE ROLEMENU_ID =  ? "; 

	public RoleMenu getRoleMenu(ResultSet rs) throws SQLException{
		RoleMenu roleMenu = new RoleMenu();

		roleMenu.setRoleId(rs.getLong("ROLE_ID"));

		roleMenu.setMenuId(rs.getLong("MENU_ID"));

		roleMenu.setAddAllowed("Y".equals(rs.getString("ADD_ALLOWED")));

		roleMenu.setModifyAllowed("Y".equals(rs.getString("MODIFY_ALLOWED")));

		roleMenu.setViewAllowed("Y".equals(rs.getString("VIEW_ALLOWED")));

		roleMenu.setDeleteAllowed("Y".equals(rs.getString("DELETE_ALLOWED")));

		roleMenu.setRolemenuId(rs.getLong("ROLEMENU_ID"));

		return roleMenu;

	}

	public void setRoleMenu(PreparedStatement psmt, RoleMenu roleMenu) throws SQLException{
		if( null == roleMenu.getRoleId()){ 
			psmt.setNull(1,Types.INTEGER); 
		} else { 
			psmt.setLong(1,roleMenu.getRoleId());
		}

		if( null == roleMenu.getMenuId()){ 
			psmt.setNull(2,Types.INTEGER); 
		} else { 
			psmt.setLong(2,roleMenu.getMenuId());
		}

		psmt.setString(3,roleMenu.isAddAllowed()?"Y":"N");

		psmt.setString(4,roleMenu.isModifyAllowed()?"Y":"N");

		psmt.setString(5,roleMenu.isViewAllowed()?"Y":"N");

		psmt.setString(6,roleMenu.isDeleteAllowed()?"Y":"N");

		if( null == roleMenu.getRolemenuId()){ 
			psmt.setNull(7,Types.INTEGER); 
		} else { 
			psmt.setLong(7,roleMenu.getRolemenuId());
		}


	}

}