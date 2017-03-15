package com.aruntech.shoppingcartfrontend.model;

public class Password 
{
	private String email;
	private String password;
	private String newPassword;
	private String cnfPassword;
	public String getPassword() 
		{
			return password;
		}
	public void setPassword(String password) 
		{
			this.password = password;
		}
	public String getNewPassword()
		{
			return newPassword;
		}
	public void setNewPassword(String newPassword) 
		{
			this.newPassword = newPassword;
		}
	public String getCnfPassword()
		{
			return cnfPassword;
		}
	public void setCnfPassword(String cnfPassword) 
		{
			this.cnfPassword = cnfPassword;
		}
	public String getEmail()
		{
			return email;
		}
	public void setEmail(String email) 
		{
			this.email = email;
		}
	
}
