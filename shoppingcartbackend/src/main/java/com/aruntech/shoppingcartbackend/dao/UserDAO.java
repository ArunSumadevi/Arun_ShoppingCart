package com.aruntech.shoppingcartbackend.dao;

import java.util.List;

import com.aruntech.shoppingcartbackend.model.User;

public interface UserDAO 
{
	public String save(User user); //Save data to DB
	public String update(User user); // Update data on DB
	public boolean delete(User user); //Delete data from DB
	public User get(String ID); // Get particular data from DB
	public  List<User> getAll(); //Get all data from DB
	public  List<User> getUsers(String exclude); //Get all data from DB excluding an email
	public User getUserEmail(String userEmailId); // Get data from db based on user Email 
	
}//**********************************************Class Ends*************************************************************
