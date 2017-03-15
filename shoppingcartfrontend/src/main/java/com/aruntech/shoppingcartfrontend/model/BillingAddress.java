package com.aruntech.shoppingcartfrontend.model;

import java.io.Serializable;

public class BillingAddress implements Serializable
{
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private static final long serialVersionUID = 4657462015039726070L;
	public String getAddress1() 
		{
			return address1;
		}
	public void setAddress1(String address1) 
		{
			this.address1 = address1;
		}
	public String getAddress2()
		{
			return address2;
		}
	public void setAddress2(String address2)
		{
			this.address2 = address2;
		}
	public String getCity()
		{
			return city;
		}
	public void setCity(String city)
		{
			this.city = city;
		}
	public String getState()
		{
			return state;
		}
	public void setState(String state)
		{
			this.state = state;
		}
	public String getCountry() 
		{
			return country;
		}
	public void setCountry(String country) 
		{
			this.country = country;
		}
	public String getPincode() 
		{
			return pincode;
		}
	public void setPincode(String pincode) 
		{
			this.pincode = pincode;
		}
}
