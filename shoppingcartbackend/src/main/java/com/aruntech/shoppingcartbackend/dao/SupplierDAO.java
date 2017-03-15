package com.aruntech.shoppingcartbackend.dao;

import java.util.List;

import com.aruntech.shoppingcartbackend.model.Supplier;

public interface SupplierDAO 
{
	public String save(Supplier supplier);
	public boolean update(Supplier supplier);
	public boolean delete(Supplier supplier);
	public Supplier get(String ID);
	public List<Supplier> getAll();
}//**********************************************Class Ends*************************************************************