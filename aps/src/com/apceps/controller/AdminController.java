package com.apceps.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apceps.domain.Menu;
import com.apceps.domain.Role;
import com.apceps.domain.User;
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
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = null;
	private RoleMenuService rmService = null;
	private MenuService menuService = null;
	private RoleService roleService = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		setUserService(new UserServiceImpl());
		setMenuService(new MenuServiceImpl());
		setRmService(new RoleMenuServiceImpl());
		setRoleService(new RoleServiceImpl());
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
       try {
		String module = request.getParameter("module");
 		List<Role> roles = roleService.loadAllRoles();
		Map<Long,String> roleMap = new HashMap<Long,String>();
		for(Role role : roles){
			roleMap.put(role.getRoleId(), role.getRoleName());
		}
		request.setAttribute("roleMap", roleMap);


 		List<Menu> menus = menuService.loadAllMenus();
		Map<Long,String> menuMap = new HashMap<Long,String>();
		for(Menu menu : menus){
			menuMap.put(menu.getMenuId(), menu.getMenuName());
		}
		request.setAttribute("menuMap", menuMap);


 		List<User> users = userService.loadAllUsers();
		Map<Long,String> userMap = new HashMap<Long,String>();
		for(User user : users){
			userMap.put(user.getUserId(), user.getUserName());
		}
		request.setAttribute("userMap", userMap);
		
		
		if("users".equalsIgnoreCase(module)) {

			handleUsers(request, response);
		 	
			
		}else if("roles".equalsIgnoreCase(module)) {

			handleRoles(request, response);
			
					
		}else if("menus".equalsIgnoreCase(module)) {

			handleMenus(request, response);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

  	}

	protected void handleMenus(HttpServletRequest request,
			HttpServletResponse response) throws Exception, ServletException,
			IOException {

		String actionName = request.getParameter("actionName");
		String menuIdStr = request.getParameter("menuId");
		if (StringUtil.isEmpty(menuIdStr))
			menuIdStr = "0";
		Long menuId = Long.parseLong(menuIdStr);

		if ("list".equalsIgnoreCase(actionName)) {

			List<Menu> menuList = menuService.loadAllMenus();
			request.setAttribute("menuList", menuList);
			RequestDispatcher rd = request
					.getRequestDispatcher("menus.jsp?tabName=list");
			rd.forward(request, response);

		} else if ("details".equalsIgnoreCase(actionName)) {

			Menu menu = menuService.loadMenuByMenuId(menuId);

			request.setAttribute("menu", menu);
			if (menu != null)
				request.setAttribute("roleMenuList",
						rmService.loadRoleMenuByMenuId(menuId));

			RequestDispatcher rd = request
					.getRequestDispatcher("menus.jsp?tabName=details");
			rd.forward(request, response);

		} else if ("save".equalsIgnoreCase(actionName)) {
			Menu menu = new Menu();
			menu.setMenuId(menuId);
			menu.setMenuName(request.getParameter("menuName"));
			menu.setMenuUrl(request.getParameter("menuUrl"));

			if (menuId.longValue() == 0) {
				menu.setMenuId(menuService.addMenu(menu));
				request.setAttribute("message", "Menu inserted successfully.");
			} else {
				menuService.updateMenu(menu);
				request.setAttribute("message", "Menu updated successfully.");
			}

			request.setAttribute("menu", menu);
			RequestDispatcher rd = request
					.getRequestDispatcher("menus.jsp?tabName=details");
			rd.forward(request, response);

		} else if ("delete".equalsIgnoreCase(actionName)) {

			if (menuId.longValue() != 0) {
				menuService.deleteMenuByMenuId(menuId);
				request.setAttribute("message", "Menu deleted successfully.");
			}

			List<Menu> menuList = menuService.loadAllMenus();
			request.setAttribute("menuList", menuList);
			RequestDispatcher rd = request
					.getRequestDispatcher("menus.jsp?tabName=list");
			rd.forward(request, response);

		}
	}

	protected void handleUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception, ServletException,
			IOException {
 		String actionName = request.getParameter("actionName");
		String userIdStr = request.getParameter("userId");
		if (StringUtil.isEmpty(userIdStr))
			userIdStr = "0";
		Long userId = Long.parseLong(userIdStr);

		if ("list".equalsIgnoreCase(actionName)) {

			List<User> userList = userService.loadAllUsers();
			request.setAttribute("userList", userList);
			RequestDispatcher rd = request
					.getRequestDispatcher("users.jsp?tabName=list");
			rd.forward(request, response);

		} else if ("details".equalsIgnoreCase(actionName)) {

			User user = userService.loadUserByUserId(userId);
			request.setAttribute("user", user);
			RequestDispatcher rd = request
					.getRequestDispatcher("users.jsp?tabName=details");
			rd.forward(request, response);

		} else if ("save".equalsIgnoreCase(actionName)) {

			User user = new User();
			user.setUserId(userId);
			user.setUserName(request.getParameter("userName"));
			user.setUserPassword(request.getParameter("userPassword"));
			user.setEmail(request.getParameter("email"));
			user.setRoleId(Long.parseLong(request.getParameter("roleId")));

			if (userId.longValue() == 0) {
				user.setUserId(userService.addUser(user));
				request.setAttribute("message", "User inserted successfully.");
			} else {
				userService.updateUser(user);
				request.setAttribute("message", "User updated successfully.");
			}

			// Role role = roleService.loadRoleByRoleId(roleId);
			request.setAttribute("user", user);
			RequestDispatcher rd = request
					.getRequestDispatcher("users.jsp?tabName=details");
			rd.forward(request, response);

		} else if ("delete".equalsIgnoreCase(actionName)) {

			if (userId.longValue() != 0) {
				userService.deleteUserByUserId(userId);
				request.setAttribute("message", "User deleted successfully.");
			}

			List<User> userList = userService.loadAllUsers();
			request.setAttribute("userList", userList);
			RequestDispatcher rd = request
					.getRequestDispatcher("users.jsp?tabName=list");
			rd.forward(request, response);

		}
	}

	protected void handleRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception, ServletException,
			IOException {

		String actionName = request.getParameter("actionName");

		String roleIdStr = request.getParameter("roleId");
		if (StringUtil.isEmpty(roleIdStr))
			roleIdStr = "0";
		Long roleId = Long.parseLong(roleIdStr);

		if ("list".equalsIgnoreCase(actionName)) {

			List<Role> roleList = roleService.loadAllRoles();
			request.setAttribute("roleList", roleList);
			RequestDispatcher rd = request
					.getRequestDispatcher("roles.jsp?tabName=list");
			rd.forward(request, response);

		} else if ("details".equalsIgnoreCase(actionName)) {

			Role role = roleService.loadRoleByRoleId(roleId);
			request.setAttribute("role", role);

			if (role != null) {
				request.setAttribute("roleMenuList",
						rmService.loadRoleMenuByRoleId(roleId));

				request.setAttribute("userList",
						userService.loadUserByRoleId(roleId));
			}

			RequestDispatcher rd = request
					.getRequestDispatcher("roles.jsp?tabName=details");
			rd.forward(request, response);

		} else if ("save".equalsIgnoreCase(actionName)) {

			Role role = new Role();
			role.setRoleId(roleId);
			role.setRoleName(request.getParameter("roleName"));
			role.setRoleDesc(request.getParameter("roleDesc"));

			if (roleId.longValue() == 0) {
				role.setRoleId(roleService.addRole(role));
				request.setAttribute("message", "Role inserted successfully.");
			} else {
				roleService.updateRole(role);
				request.setAttribute("message", "Role updated successfully.");
			}

			// Role role = roleService.loadRoleByRoleId(roleId);
			request.setAttribute("role", role);
			RequestDispatcher rd = request
					.getRequestDispatcher("roles.jsp?tabName=details");
			rd.forward(request, response);

		} else if ("delete".equalsIgnoreCase(actionName)) {

			if (roleId.longValue() != 0) {
				roleService.deleteRoleByRoleId(roleId);
				request.setAttribute("message", "Role deleted successfully.");
			}

			List<Role> roleList = roleService.loadAllRoles();
			// request.setAttribute("roleList", roleList);
			RequestDispatcher rd = request
					.getRequestDispatcher("roles.jsp?tabName=list");
			rd.forward(request, response);
		}
	}

}
