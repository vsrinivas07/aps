package com.apceps.dao.cg;

import com.apceps.domain.Product;
import java.util.List;

public interface ProductDaoBase {

	public Product loadProductByProductId(Long productId) throws Exception;

	public void deleteProductByProductId(Long productId) throws Exception;

	public Product loadProductByProductName(String productName) throws Exception;

	public void deleteProductByProductName(String productName) throws Exception;

	public List<Product> loadProductByMfdBy(Long mfdBy) throws Exception;

	public void deleteProductByMfdBy(Long mfdBy) throws Exception;

	public List<Product> loadAllProducts() throws Exception;

	public Long addProduct(Product product) throws Exception;

	public void updateProduct(Product product) throws Exception;

}