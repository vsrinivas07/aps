package com.apceps.service.cg.impl;

import com.apceps.domain.Product;
import com.apceps.dao.cg.ProductDaoBase;
import com.apceps.dao.cg.impl.ProductDaoBaseImpl;
import com.apceps.service.cg.ProductServiceBase;
import com.apceps.domain.Product;
import java.util.List;

public class ProductServiceBaseImpl implements ProductServiceBase {

	private ProductDaoBase productDaoBase = null; 

	public ProductDaoBase getProductDaoBase() {
		return productDaoBase;
	}

	public void setProductDaoBase(ProductDaoBase productDaoBase) {
		this.productDaoBase = productDaoBase;
	}

	public ProductServiceBaseImpl() {
		this.productDaoBase=new ProductDaoBaseImpl();
	}

	public Product loadProductByProductId(Long productId) throws Exception{
		return productDaoBase.loadProductByProductId(productId);
	}

	public void deleteProductByProductId(Long productId) throws Exception{
		productDaoBase.deleteProductByProductId(productId);
	}

	public Product loadProductByProductName(String productName) throws Exception{
		return productDaoBase.loadProductByProductName(productName);
	}

	public void deleteProductByProductName(String productName) throws Exception{
		productDaoBase.deleteProductByProductName(productName);
	}

	public List<Product> loadProductByMfdBy(Long mfdBy) throws Exception{
		return productDaoBase.loadProductByMfdBy(mfdBy);
	}

	public void deleteProductByMfdBy(Long mfdBy) throws Exception{
		productDaoBase.deleteProductByMfdBy(mfdBy);
	}

	public List<Product> loadAllProducts() throws Exception{
		return productDaoBase.loadAllProducts();
	}

	public Long addProduct(Product product) throws Exception{
		return productDaoBase.addProduct(product);
	}

	public void updateProduct(Product product) throws Exception{
		productDaoBase.updateProduct(product);
	}

}