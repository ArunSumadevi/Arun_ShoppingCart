package com.aruntech.shoppingcartfrontend.model;

public class ProductCart 
{

	private String userId;
	private String productId;
	
	public String getUserId() 
		{
			return userId;
		}
	public void setUserId(String userId) 
		{
			this.userId = userId;
		}
	public String getProductId()
		{
			return productId;
		}
	public void setProductId(String productId)
		{
			this.productId = productId;
		}
}
