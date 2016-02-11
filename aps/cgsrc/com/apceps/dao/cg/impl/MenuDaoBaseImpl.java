package com.apceps.dao.cg.impl;

import com.apceps.domain.Menu;
import com.apceps.domain.cg.MenuHelperBase;
import com.apceps.dao.cg.MenuDaoBase;
import com.apceps.domain.MenuHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class MenuDaoBaseImpl implements MenuDaoBase {

	private MenuHelper menuHelper = null; 

	public MenuDaoBaseImpl() {
		this.menuHelper=new MenuHelper();
	}

	public MenuHelper getMenuHelper() {
		return menuHelper;
	}

	public void setMenuHelper(MenuHelper menuHelper) {
		this.menuHelper = menuHelper;
	}

	public Menu loadMenuByMenuId(Long menuId) throws Exception{
		String sql = " SELECT * FROM AS_MENU WHERE MENU_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Menu menu = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,menuId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			menu = menuHelper.getMenu(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return menu;
	}

	public void deleteMenuByMenuId(Long menuId) throws Exception{
		String sql = MenuHelper.DELETE_MENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,menuId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public Menu loadMenuByMenuName(String menuName) throws Exception{
		String sql = " SELECT * FROM AS_MENU WHERE MENU_NAME = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Menu menu = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,menuName);
			rs=pstmt.executeQuery();

			while(rs.next()){
			menu = menuHelper.getMenu(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return menu;
	}

	public void deleteMenuByMenuName(String menuName) throws Exception{
		String sql = MenuHelper.DELETE_MENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,menuName);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Menu> loadAllMenus() throws Exception{
		String sql = " SELECT * FROM AS_MENU " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Menu> menuList = new ArrayList<Menu>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			menuList.add(menuHelper.getMenu(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return menuList;
	}

	public Long addMenu(Menu menu) throws Exception{
		String sql = MenuHelper.INSERT_MENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			menu.setMenuId(DBUtil.getId(MenuHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			menuHelper.setMenu(pstmt,menu);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return menu.getMenuId();
	}

	public void updateMenu(Menu menu) throws Exception{
		String sql = MenuHelper.UPDATE_MENU_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			menuHelper.setMenu(pstmt,menu);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}