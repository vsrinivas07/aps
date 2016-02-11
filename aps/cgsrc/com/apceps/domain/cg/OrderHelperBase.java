package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.Order;

public class OrderHelperBase {

	public static final String SEQ_KEY = "AS_ORDER_SEQ";

	public static final String INSERT_ORDER_SQL = " INSERT INTO AS_ORDER (PRODUCT_ID ,QUANTITY ,ORDERED_BY ,ORDER_DATE ,DELIVERY_LOCATION ,PROD_PICKUP_DATE ,WHS_REACH_DATE ,WHS_PICKUP_DATE ,DELIVERY_DATE ,RECEIVED_BY ,ORDER_STATUS ,ORDER_ID ) VALUES ( ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";

	public static final String UPDATE_ORDER_SQL = " UPDATE AS_ORDER SET PRODUCT_ID = ?  , QUANTITY = ?  , ORDERED_BY = ?  , ORDER_DATE = ?  , DELIVERY_LOCATION = ?  , PROD_PICKUP_DATE = ?  , WHS_REACH_DATE = ?  , WHS_PICKUP_DATE = ?  , DELIVERY_DATE = ?  , RECEIVED_BY = ?  , ORDER_STATUS = ?  WHERE ORDER_ID =  ? ";

	public static final String DELETE_ORDER_SQL = " DELETE FROM  AS_ORDER WHERE ORDER_ID =  ? ";

	public Order getOrder(ResultSet rs) throws SQLException {
		Order order = new Order();

		order.setProductId(rs.getLong("PRODUCT_ID"));

		order.setQuantity(rs.getLong("QUANTITY"));

		order.setOrderedBy(rs.getLong("ORDERED_BY"));

		order.setOrderDate(rs.getDate("ORDER_DATE"));

		order.setDeliveryLocation(rs.getLong("DELIVERY_LOCATION"));

		order.setProdPickupDate(rs.getDate("PROD_PICKUP_DATE"));

		order.setWhsReachDate(rs.getDate("WHS_REACH_DATE"));

		order.setWhsPickupDate(rs.getDate("WHS_PICKUP_DATE"));

		order.setDeliveryDate(rs.getDate("DELIVERY_DATE"));

		order.setReceivedBy(rs.getString("RECEIVED_BY"));

		order.setOrderStatus(rs.getString("ORDER_STATUS"));

		order.setOrderId(rs.getLong("ORDER_ID"));

		return order;

	}

	public void setOrder(PreparedStatement psmt, Order order)
			throws SQLException {
		if (null == order.getProductId()) {
			psmt.setNull(1, Types.INTEGER);
		} else {
			psmt.setLong(1, order.getProductId());
		}

		if (null == order.getQuantity()) {
			psmt.setNull(2, Types.INTEGER);
		} else {
			psmt.setLong(2, order.getQuantity());
		}

		if (null == order.getOrderedBy()) {
			psmt.setNull(3, Types.INTEGER);
		} else {
			psmt.setLong(3, order.getOrderedBy());
		}

		if (null == order.getOrderDate()) {
			psmt.setNull(4, Types.DATE);
		} else {
			psmt.setDate(4, order.getOrderDate());
		}

		if (null == order.getDeliveryLocation()) {
			psmt.setNull(5, Types.INTEGER);
		} else {
			psmt.setLong(5, order.getDeliveryLocation());
		}

		if (null == order.getProdPickupDate()) {
			psmt.setNull(6, Types.DATE);
		} else {
			psmt.setDate(6, order.getProdPickupDate());
		}

		if (null == order.getWhsReachDate()) {
			psmt.setNull(7, Types.DATE);
		} else {
			psmt.setDate(7, order.getWhsReachDate());
		}

		if (null == order.getWhsPickupDate()) {
			psmt.setNull(8, Types.DATE);
		} else {
			psmt.setDate(8, order.getWhsPickupDate());
		}

		if (null == order.getDeliveryDate()) {
			psmt.setNull(9, Types.DATE);
		} else {
			psmt.setDate(9, order.getDeliveryDate());
		}

		if (null == order.getReceivedBy()) {
			psmt.setNull(10, Types.VARCHAR);
		} else {
			psmt.setString(10, order.getReceivedBy());
		}

		if (null == order.getOrderStatus()) {
			psmt.setNull(11, Types.VARCHAR);
		} else {
			psmt.setString(11, order.getOrderStatus());
		}

		if (null == order.getOrderId()) {
			psmt.setNull(12, Types.INTEGER);
		} else {
			psmt.setLong(12, order.getOrderId());
		}

	}

}