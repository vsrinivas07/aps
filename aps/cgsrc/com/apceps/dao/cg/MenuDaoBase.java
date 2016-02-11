package com.apceps.dao.cg;

import com.apceps.domain.Menu;
import java.util.List;

public interface MenuDaoBase {

	public Menu loadMenuByMenuId(Long menuId) throws Exception;

	public void deleteMenuByMenuId(Long menuId) throws Exception;

	public Menu loadMenuByMenuName(String menuName) throws Exception;

	public void deleteMenuByMenuName(String menuName) throws Exception;

	public List<Menu> loadAllMenus() throws Exception;

	public Long addMenu(Menu menu) throws Exception;

	public void updateMenu(Menu menu) throws Exception;

}