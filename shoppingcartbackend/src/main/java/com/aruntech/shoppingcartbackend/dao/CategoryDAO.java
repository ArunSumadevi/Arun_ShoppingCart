package com.aruntech.shoppingcartbackend.dao;

import java.util.List;

import com.aruntech.shoppingcartbackend.model.Category;

public interface CategoryDAO 
{
	public String save(Category category); //Save data to DB
	public boolean update(Category category); // Update data on DB
	public boolean delete(Category category); //Delete data from DB
	public Category get(String ID); // Get particular data from DB
	public List<Category> getAll(); //Get all data from DB
}//**********************************************Class Ends*************************************************************