package com.aruntech.shoppingcartbackend.dao;

import java.util.List;

import com.aruntech.shoppingcartbackend.model.ShoppingCart;

public interface ShoppingCartDAO 
{
	public String save(ShoppingCart shoppingCart); //Save data to DB
	public boolean update(ShoppingCart shoppingCart); // Update data on DB
	public boolean delete(ShoppingCart shoppingCart); //Delete data from DB
	public ShoppingCart get(String ID); // Get particular data from DB
	public List<ShoppingCart> getAll(); //Get all data from DB
	public List<ShoppingCart> getUserCart(String userId); //Get cart data from DB based on user
	public boolean isPresent(String userId,String productId);
	public int getCount(String userId);
}//**********************************************Class Ends*************************************************************