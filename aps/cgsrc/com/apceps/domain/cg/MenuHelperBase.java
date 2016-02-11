package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.Menu;

public class MenuHelperBase {

	public static final String SEQ_KEY = "AS_MENU_SEQ"; 

	public static final String INSERT_MENU_SQL = " INSERT INTO AS_MENU (MENU_NAME ,MENU_URL ,MENU_ID ) VALUES ( ? ,? ,?)"; 

	public static final String UPDATE_MENU_SQL = " UPDATE AS_MENU SET MENU_NAME = ?  , MENU_URL = ?  WHERE MENU_ID =  ? "; 

	public static final String DELETE_MENU_SQL = " DELETE FROM  AS_MENU WHERE MENU_ID =  ? "; 

	public Menu getMenu(ResultSet rs) throws SQLException{
		Menu menu = new Menu();

		menu.setMenuName(rs.getString("MENU_NAME"));

		menu.setMenuUrl(rs.getString("MENU_URL"));

		menu.setMenuId(rs.getLong("MENU_ID"));

		return menu;

	}

	public void setMenu(PreparedStatement psmt, Menu menu) throws SQLException{
		if( null == menu.getMenuName()){ 
			psmt.setNull(1,Types.VARCHAR); 
		} else { 
			psmt.setString(1,menu.getMenuName());
		}

		if( null == menu.getMenuUrl()){ 
			psmt.setNull(2,Types.VARCHAR); 
		} else { 
			psmt.setString(2,menu.getMenuUrl());
		}

		if( null == menu.getMenuId()){ 
			psmt.setNull(3,Types.INTEGER); 
		} else { 
			psmt.setLong(3,menu.getMenuId());
		}


	}

}