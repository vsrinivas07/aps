package com.apceps.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apceps.domain.Order;
import com.apceps.domain.Product;
import com.apceps.domain.User;
import com.apceps.domain.UserCdo;
import com.apceps.service.OrderService;
import com.apceps.service.ProductService;
import com.apceps.service.UserService;
import com.apceps.service.impl.OrderServiceImpl;
import com.apceps.service.impl.ProductServiceImpl;
import com.apceps.service.impl.UserServiceImpl;
import com.apceps.util.StringUtil;

/**
 * Servlet implementation class OrderController
 */
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = null ; 

	private OrderService orderService = null ; 

	private UserService userService = null;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		setProductService(new ProductServiceImpl());
        setOrderService(new OrderServiceImpl());
        setUserService(new UserServiceImpl());
	}
	 
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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


 		List<Product> products = productService.loadAllProducts();
		Map<Long,String> productMap = new HashMap<Long,String>();
		for(Product product : products){
			productMap.put(product.getProductId(), product.getProductName());
		}
		request.setAttribute("productMap", productMap);
		

 		List<User> users = userService.loadAllUsers();
		Map<Long,String> userMap = new HashMap<Long,String>();
		for(User user : users){
			userMap.put(user.getUserId(), user.getUserName());
		}
		request.setAttribute("userMap", userMap);

		
		
		if("orders".equalsIgnoreCase(module)) {

			handleOrders(request, response);
		 	
			
		} 			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

  	}

	 

	protected void handleOrders(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {

		UserCdo userCdo = (UserCdo) request.getSession().getAttribute("userCdo");
		User user = userCdo.getUser();

		String actionName = request.getParameter("actionName");
		String orderIdStr = request.getParameter("orderId") ; 
	 	if(StringUtil.isEmpty(orderIdStr)) orderIdStr = "0" ;
	 	
	 	
		Long orderId = Long.parseLong(orderIdStr);
		 
		if("list".equalsIgnoreCase(actionName)){
			
			List<Order> orderList = null ; 
			//Customer
			if(user.getRoleId() == 2 ) {
				orderList = orderService.loadOrderByOrderedBy(user.getUserId());
			}else if(user.getRoleId() == 3 ) {
				orderList = orderService.loadAllOrders();
			}else if(user.getRoleId() == 4 ) {
				
				
				// ALL products for Man
				List<Product> prodList = productService.loadProductByMfdBy(user.getUserId());
				
				// build prod ids
				List<Long> prodIds = new ArrayList<Long>();
				for(Product prod : prodList) {
					prodIds.add(prod.getProductId());
				}
				 
				
				
				 // all order
				 List<Order> allOrders = orderService.loadAllOrders();
				
				 // put man order to orderList ignore rest 
				 orderList = new ArrayList<Order>();
					
				 for(Order order : allOrders){
					if( prodIds.contains( order.getProductId())) orderList.add(order); 
				}
				
				
				
			}
			
 		 	request.setAttribute("orderList", orderList);
			RequestDispatcher rd = request.getRequestDispatcher("orders.jsp?tabName=list");
			rd.forward(request, response);
			
			
			
		}else if("details".equalsIgnoreCase(actionName)){
		 		
			Order order= orderService.loadOrderByOrderId(orderId);
 			
			request.setAttribute("order", order);
 			//if(menu!=null) request.setAttribute("roleMenuList", rmService.loadRoleMenuByMenuId(menuId));
 			
 			RequestDispatcher rd = request.getRequestDispatcher("orders.jsp?tabName=details");
			rd.forward(request, response);
		
		} else if("save".equalsIgnoreCase(actionName)){
			Order order = new Order();
			
			order.setOrderId(orderId);
			order.setOrderedBy(user.getUserId());
			order.setProductId(Long.parseLong(request.getParameter("productId")));
			order.setOrderDate(new Date(System.currentTimeMillis()));
			order.setQuantity(Long.parseLong(request.getParameter("quantity")));
		 
			if(orderId.longValue()==0) {
				order.setOrderId(orderService.addOrder(order));
				request.setAttribute("message", "Order inserted successfully.");
			}
			else {
				orderService.updateOrder(order);
				request.setAttribute("message", "Order updated successfully.");
			}
			
			request.setAttribute("order", order);
			RequestDispatcher rd = request.getRequestDispatcher("orders.jsp?tabName=details");
			rd.forward(request, response);
			
		} else if("delete".equalsIgnoreCase(actionName)){
		
			if(orderId.longValue()!=0) {
				orderService.deleteOrderByOrderId(orderId);
				request.setAttribute("message", "Order deleted successfully.");
			}
			
			List<Order> orderList = orderService.loadOrderByOrderedBy(user.getUserId());
			request.setAttribute("orderList", orderList);
			RequestDispatcher rd = request.getRequestDispatcher("orders.jsp?tabName=list");
			rd.forward(request, response);
				
		}
		
 
	}

	
}
