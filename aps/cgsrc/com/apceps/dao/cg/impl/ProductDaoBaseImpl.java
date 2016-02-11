package com.apceps.dao.cg.impl;

import com.apceps.domain.Product;
import com.apceps.domain.cg.ProductHelperBase;
import com.apceps.dao.cg.ProductDaoBase;
import com.apceps.domain.ProductHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import com.apceps.util.ConnectionUtil;
import com.apceps.util.DBUtil;

public class ProductDaoBaseImpl implements ProductDaoBase {

	private ProductHelper productHelper = null; 

	public ProductDaoBaseImpl() {
		this.productHelper=new ProductHelper();
	}

	public ProductHelper getProductHelper() {
		return productHelper;
	}

	public void setProductHelper(ProductHelper productHelper) {
		this.productHelper = productHelper;
	}

	public Product loadProductByProductId(Long productId) throws Exception{
		String sql = " SELECT * FROM AS_PRODUCT WHERE PRODUCT_ID = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Product product = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,productId);
			rs=pstmt.executeQuery();

			while(rs.next()){
			product = productHelper.getProduct(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return product;
	}

	public void deleteProductByProductId(Long productId) throws Exception{
		String sql = ProductHelper.DELETE_PRODUCT_SQL;
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

	public Product loadProductByProductName(String productName) throws Exception{
		String sql = " SELECT * FROM AS_PRODUCT WHERE PRODUCT_NAME = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		Product product = null;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,productName);
			rs=pstmt.executeQuery();

			while(rs.next()){
			product = productHelper.getProduct(rs);
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return product;
	}

	public void deleteProductByProductName(String productName) throws Exception{
		String sql = ProductHelper.DELETE_PRODUCT_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,productName);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Product> loadProductByMfdBy(Long mfdBy) throws Exception{
		String sql = " SELECT * FROM AS_PRODUCT WHERE MFD_BY = ?  " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Product> productList = new ArrayList<Product>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,mfdBy);
			rs=pstmt.executeQuery();

			while(rs.next()){
			productList.add(productHelper.getProduct(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return productList;
	}

	public void deleteProductByMfdBy(Long mfdBy) throws Exception{
		String sql = ProductHelper.DELETE_PRODUCT_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1,mfdBy);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

	public List<Product> loadAllProducts() throws Exception{
		String sql = " SELECT * FROM AS_PRODUCT " ;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		List<Product> productList = new ArrayList<Product>();
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
			productList.add(productHelper.getProduct(rs));
			 
			 
			}
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,rs,pstmt);
	  }
		return productList;
	}

	public Long addProduct(Product product) throws Exception{
		String sql = ProductHelper.INSERT_PRODUCT_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			product.setProductId(DBUtil.getId(ProductHelper.SEQ_KEY));
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			productHelper.setProduct(pstmt,product);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }
		return product.getProductId();
	}

	public void updateProduct(Product product) throws Exception{
		String sql = ProductHelper.UPDATE_PRODUCT_SQL;
		Connection connection = null ; 
		PreparedStatement pstmt = null ;
		try { 
			connection = ConnectionUtil.getConnection();
			pstmt=connection.prepareStatement(sql);
			productHelper.setProduct(pstmt,product);
			pstmt.executeUpdate();
			 
			 
	  } catch(Exception ex) { 

		  throw ex; 

	  } finally {
		  ConnectionUtil.closeConnection(connection,pstmt);
	  }

	}

}