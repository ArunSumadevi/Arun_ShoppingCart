package com.aruntech.shoppingcartbackend.dao;

import java.util.List;

import com.aruntech.shoppingcartbackend.model.Product;

public interface ProductDAO 
{
	public String save(Product product); //Save data to DB
	public boolean update(Product product); // Update data on DB
	public boolean delete(Product product); //Delete data from DB
	public Product get(String ID); // Get particular data from DB
	public List<Product> getAll(); //Get all data from DB
	public List<Product> carouselList();
	public List<String> productNames();// Search a product in the data base
}//**********************************************Class Ends*************************************************************