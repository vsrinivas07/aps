package com.apceps.dao.cg.impl;

import com.apceps.domain.Order;
import com.apceps.domain.cg.OrderHelperBase;
import com.apceps.dao.cg.OrderDaoBase;
import com.apceps.domain.OrderHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class OrderDaoBaseImpl implements OrderDaoBase {

	private OrderHelper orderHelper = null; 

	public OrderDaoBaseImpl() {
		this.orderHelper=new OrderHelper();
	}

	public OrderHelper getOrderHelper() {
		return orderHelper;
	}

	public void setOrderHelper(OrderHelper orderHelper) {
		this.orderHelper = orderHelper;
	}

	public Order loadOrderByOrderId(Long orderId) throws Exception{
		String sql = " SELECT * FROM AS_ORDER WHERE ORDER_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Order order = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,orderId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			order = orderHelper.getOrder(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return order;
	}

	public void deleteOrderByOrderId(Long orderId) throws Exception{
		String sql = OrderHelper.DELETE_ORDER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,orderId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Order> loadOrderByProductId(Long productId) throws Exception{
		String sql = " SELECT * FROM AS_ORDER WHERE PRODUCT_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Order> orderList = new ArrayList<Order>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,productId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			orderList.add(orderHelper.getOrder(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return orderList;
	}

	public void deleteOrderByProductId(Long productId) throws Exception{
		String sql = OrderHelper.DELETE_ORDER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,productId);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Order> loadOrderByOrderedBy(Long orderedBy) throws Exception{
		String sql = " SELECT * FROM AS_ORDER WHERE ORDERED_BY = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Order> orderList = new ArrayList<Order>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,orderedBy);
			rs=pstmt.executeQuery();

			while(rs.next()){
			orderList.add(orderHelper.getOrder(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return orderList;
	}

	public void deleteOrderByOrderedBy(Long orderedBy) throws Exception{
		String sql = OrderHelper.DELETE_ORDER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,orderedBy);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Order> loadOrderByDeliveryLocation(Long deliveryLocation) throws Exception{
		String sql = " SELECT * FROM AS_ORDER WHERE DELIVERY_LOCATION = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Order> orderList = new ArrayList<Order>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,deliveryLocation);
			rs=pstmt.executeQuery();

			while(rs.next()){
			orderList.add(orderHelper.getOrder(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return orderList;
	}

	public void deleteOrderByDeliveryLocation(Long deliveryLocation) throws Exception{
		String sql = OrderHelper.DELETE_ORDER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,deliveryLocation);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Order> loadAllOrders() throws Exception{
		String sql = " SELECT * FROM AS_ORDER " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Order> orderList = new ArrayList<Order>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			orderList.add(orderHelper.getOrder(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return orderList;
	}

	public Long addOrder(Order order) throws Exception{
		String sql = OrderHelper.INSERT_ORDER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			order.setOrderId(DBUtil.getId(OrderHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			orderHelper.setOrder(pstmt,order);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return order.getOrderId();
	}

	public void updateOrder(Order order) throws Exception{
		String sql = OrderHelper.UPDATE_ORDER_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			orderHelper.setOrder(pstmt,order);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}