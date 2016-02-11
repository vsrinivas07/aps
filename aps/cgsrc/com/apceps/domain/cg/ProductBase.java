package com.apceps.domain.cg;

public class ProductBase {

	private Long productId = null; 

	private String productName = null; 

	private String productDesc = null; 

	private Double rate = null; 

	private Long mfdBy = null; 

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Long getMfdBy() {
		return mfdBy;
	}

	public void setMfdBy(Long mfdBy) {
		this.mfdBy = mfdBy;
	}

}