package com.apceps.service.cg.impl;

import com.apceps.domain.Menu;
import com.apceps.dao.cg.MenuDaoBase;
import com.apceps.dao.cg.impl.MenuDaoBaseImpl;
import com.apceps.service.cg.MenuServiceBase;
import com.apceps.domain.Menu;
import java.util.List;

public class MenuServiceBaseImpl implements MenuServiceBase {

	private MenuDaoBase menuDaoBase = null; 

	public MenuDaoBase getMenuDaoBase() {
		return menuDaoBase;
	}

	public void setMenuDaoBase(MenuDaoBase menuDaoBase) {
		this.menuDaoBase = menuDaoBase;
	}

	public MenuServiceBaseImpl() {
		this.menuDaoBase=new MenuDaoBaseImpl();
	}

	public Menu loadMenuByMenuId(Long menuId) throws Exception{
		return menuDaoBase.loadMenuByMenuId(menuId);
	}

	public void deleteMenuByMenuId(Long menuId) throws Exception{
		menuDaoBase.deleteMenuByMenuId(menuId);
	}

	public Menu loadMenuByMenuName(String menuName) throws Exception{
		return menuDaoBase.loadMenuByMenuName(menuName);
	}

	public void deleteMenuByMenuName(String menuName) throws Exception{
		menuDaoBase.deleteMenuByMenuName(menuName);
	}

	public List<Menu> loadAllMenus() throws Exception{
		return menuDaoBase.loadAllMenus();
	}

	public Long addMenu(Menu menu) throws Exception{
		return menuDaoBase.addMenu(menu);
	}

	public void updateMenu(Menu menu) throws Exception{
		menuDaoBase.updateMenu(menu);
	}

}