package com.apceps.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apceps.domain.Menu;
import com.apceps.domain.Role;
import com.apceps.domain.RoleMenu;
import com.apceps.domain.User;
import com.apceps.domain.UserCdo;
import com.apceps.exception.InvalidCredentialsException;
import com.apceps.service.MenuService;
import com.apceps.service.RoleMenuService;
import com.apceps.service.RoleService;
import com.apceps.service.UserService;
import com.apceps.service.impl.MenuServiceImpl;
import com.apceps.service.impl.RoleMenuServiceImpl;
import com.apceps.service.impl.RoleServiceImpl;
import com.apceps.service.impl.UserServiceImpl;
import com.apceps.util.StringUtil;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	
	private UserService userService  = null ; 
	private RoleService roleService  = null ; 
	private RoleMenuService rmService  = null ; 
	private MenuService menuService  = null ; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super(); 
	    setUserService(new UserServiceImpl());
	    setRoleService(new RoleServiceImpl());
	    setMenuService(new MenuServiceImpl());
	    setRmService(new RoleMenuServiceImpl());
	}
	
	public UserService getUserService() {
		return userService;
	}

 	public void setUserService(UserService userService) {
		this.userService = userService;
	}
 	
 	
 
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleMenuService getRmService() {
		return rmService;
	}
 
	public void setRmService(RoleMenuService rmService) {
		this.rmService = rmService;
	}

 	public MenuService getMenuService() {
		return menuService;
	}

 	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
 		
		try {
			String action = request.getParameter("actionName");
			
			if("login".equals(action)) loadUser(request, response);
			
			else if("logoff".equals(action)) logoff(request, response);
	 
		} catch (Exception e) {
			throw new ServletException(e);
		}
 	
	}
	
	
	private void logoff(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		response.sendRedirect("logout.jsp");
	}

	public void loadUser(HttpServletRequest request,  HttpServletResponse response) throws Exception {
		String nextPage = "index.page" ; 
 		
		try {
		   	
			String userName = request.getParameter("userName");
			String userPassword = request.getParameter("userPassword");
			
			if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPassword)) throw new InvalidCredentialsException("User Name and Password are required.");
			
			User user = userService.loadUserByUserName(userName);
			
			if (null == user)  throw new InvalidCredentialsException("Invalid User Name.");

			if(!userPassword.equals(user.getUserPassword())) throw new InvalidCredentialsException("Invalid Password.");
			
			Role role = roleService.loadRoleByRoleId(user.getRoleId());
			
			List<RoleMenu> roleMenuList = rmService.loadRoleMenuByRoleId(user.getRoleId());
			
			List<Menu> menuList = new ArrayList<Menu>();
			
			for (RoleMenu rm : roleMenuList) {
				menuList.add(menuService.loadMenuByMenuId(rm.getMenuId()));
			}
			
			UserCdo userCdo = new UserCdo(user,role, menuList, roleMenuList);
			request.getSession().setAttribute("userCdo", userCdo);
			nextPage = "home.jsp";
		}  catch (InvalidCredentialsException e) {
			request.getSession().setAttribute("message", e.getMessage());
			nextPage = "index.jsp" ;
		}    
		
		response.sendRedirect(nextPage);
		
	}
}
