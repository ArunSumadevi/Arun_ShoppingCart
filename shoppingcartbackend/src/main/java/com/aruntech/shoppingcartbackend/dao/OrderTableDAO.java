package com.aruntech.shoppingcartbackend.dao;

import java.util.List;

import com.aruntech.shoppingcartbackend.model.OrderTable;

public interface OrderTableDAO 
{
	public boolean save(OrderTable orderTable); //Save data to DB
	public boolean update(OrderTable orderTable); //Update data in DB
	public List<OrderTable> getUserOrder(String userId); //Get order data from DB based on user
	public List<OrderTable> getByOrderNumber(String orderNumber); //Get order data from DB based on order number
	public OrderTable get(String ID);
	public List<OrderTable> getOrderStatus(String orderNumber); //Get order data from DB based on user
}//**********************************************Class Ends*************************************************************