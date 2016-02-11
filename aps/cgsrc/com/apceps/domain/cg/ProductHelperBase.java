package com.apceps.domain.cg;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import com.apceps.domain.Product;

public class ProductHelperBase {

	public static final String SEQ_KEY = "AS_PRODUCT_SEQ"; 

	public static final String INSERT_PRODUCT_SQL = " INSERT INTO AS_PRODUCT (PRODUCT_NAME ,PRODUCT_DESC ,RATE ,MFD_BY ,PRODUCT_ID ) VALUES ( ? ,? ,? ,? ,?)"; 

	public static final String UPDATE_PRODUCT_SQL = " UPDATE AS_PRODUCT SET PRODUCT_NAME = ?  , PRODUCT_DESC = ?  , RATE = ?  , MFD_BY = ?  WHERE PRODUCT_ID =  ? "; 

	public static final String DELETE_PRODUCT_SQL = " DELETE FROM  AS_PRODUCT WHERE PRODUCT_ID =  ? "; 

	public Product getProduct(ResultSet rs) throws SQLException{
		Product product = new Product();

		product.setProductName(rs.getString("PRODUCT_NAME"));

		product.setProductDesc(rs.getString("PRODUCT_DESC"));

		product.setRate(rs.getDouble("RATE"));

		product.setMfdBy(rs.getLong("MFD_BY"));

		product.setProductId(rs.getLong("PRODUCT_ID"));

		return product;

	}

	public void setProduct(PreparedStatement psmt, Product product) throws SQLException{
		if( null == product.getProductName()){ 
			psmt.setNull(1,Types.VARCHAR); 
		} else { 
			psmt.setString(1,product.getProductName());
		}

		if( null == product.getProductDesc()){ 
			psmt.setNull(2,Types.VARCHAR); 
		} else { 
			psmt.setString(2,product.getProductDesc());
		}

		if( null == product.getRate()){ 
			psmt.setNull(3,Types.DECIMAL); 
		} else { 
			psmt.setDouble(3,product.getRate());
		}

		if( null == product.getMfdBy()){ 
			psmt.setNull(4,Types.INTEGER); 
		} else { 
			psmt.setLong(4,product.getMfdBy());
		}

		if( null == product.getProductId()){ 
			psmt.setNull(5,Types.INTEGER); 
		} else { 
			psmt.setLong(5,product.getProductId());
		}


	}

}