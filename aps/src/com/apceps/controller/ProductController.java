package com.apceps.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apceps.domain.Menu;
import com.apceps.domain.Product;
import com.apceps.domain.User;
import com.apceps.domain.UserCdo;
import com.apceps.service.ProductService;
import com.apceps.service.UserService;
import com.apceps.service.impl.ProductServiceImpl;
import com.apceps.service.impl.UserServiceImpl;
import com.apceps.util.StringUtil;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = null ; 
	private UserService userService = null;

	 
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
		setProductService(new ProductServiceImpl());
		setUserService(new UserServiceImpl());
	}
	 
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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
			
	 		List<User> users = userService.loadAllUsers();
			Map<Long,String> userMap = new HashMap<Long,String>();
			for(User user : users){
				userMap.put(user.getUserId(), user.getUserName());
			}
			request.setAttribute("userMap", userMap);

			
			if ("products".equalsIgnoreCase(module)) {

				handleProducts(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

	}

	protected void handleProducts(HttpServletRequest request, HttpServletResponse response) throws Exception, ServletException, IOException {

		UserCdo userCdo = (UserCdo) request.getSession().getAttribute("userCdo");
		User user = userCdo.getUser();

		String actionName = request.getParameter("actionName");
		String productIdStr = request.getParameter("productId") ; 
	 	if(StringUtil.isEmpty(productIdStr)) productIdStr = "0" ; 
		Long productId = Long.parseLong(productIdStr);
		 
		if("list".equalsIgnoreCase(actionName)){
			
			List<Product> productList = null ; 
	        //Man	 
  		 	if(user.getRoleId() == 4 ) {
				productList = productService.loadProductByMfdBy(user.getUserId());
			}else {
				productList = productService.loadAllProducts() ; 
			}
 		 	request.setAttribute("productList", productList);
			RequestDispatcher rd = request.getRequestDispatcher("products.jsp?tabName=list");
			rd.forward(request, response);
			
		}else if("details".equalsIgnoreCase(actionName)){
		 		
			Product product= productService.loadProductByProductId(productId);
 			
			request.setAttribute("product", product);
 			//if(menu!=null) request.setAttribute("roleMenuList", rmService.loadRoleMenuByMenuId(menuId));
 			
 			RequestDispatcher rd = request.getRequestDispatcher("products.jsp?tabName=details");
			rd.forward(request, response);
		
		} else if("save".equalsIgnoreCase(actionName)){
			Product product = new Product();
			product.setProductId(productId);
			product.setProductName(request.getParameter("productName"));
			product.setProductDesc(request.getParameter("productDesc"));
			product.setMfdBy(user.getUserId());
			product.setRate(Double.parseDouble(request.getParameter("rate")));
			
			
			if(productId.longValue()==0) {
				product.setProductId(productService.addProduct(product));
				request.setAttribute("message", "Product inserted successfully.");
			}
			else {
				productService.updateProduct(product);
				request.setAttribute("message", "Product updated successfully.");
			}
			
			request.setAttribute("product", product);
			RequestDispatcher rd = request.getRequestDispatcher("products.jsp?tabName=details");
			rd.forward(request, response);
			
		} else if("delete".equalsIgnoreCase(actionName)){
		
			if(productId.longValue()!=0) {
				productService.deleteProductByProductId(productId);
				request.setAttribute("message", "Product deleted successfully.");
			}
			

			List<Product> productList = productService.loadProductByMfdBy(user.getUserId());
			request.setAttribute("productList", productList);
			RequestDispatcher rd = request.getRequestDispatcher("products.jsp?tabName=list");
			rd.forward(request, response);
				
		}if("listAll".equalsIgnoreCase(actionName)){
			List<Product> productList = productService.loadAllProducts();
		 	request.setAttribute("productList", productList);
			RequestDispatcher rd = request.getRequestDispatcher("products.jsp?tabName=list");
			rd.forward(request, response);
			
		}
	}

	
}
