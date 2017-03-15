package com.aruntech.shoppingcartbackend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity

@Table(name="Supplier")

@Component
public class Supplier implements Serializable{

	@Id
	private String id;
	private String name;
	private String mobile;
	private String address;
	private String description;
	private String email;
	private String addedOn;
	
	@OneToMany(mappedBy="supplier",fetch=FetchType.EAGER)
	public Set<Product> products;
	private static final long serialVersionUID = 4657462015039726023L;
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddedOn() {
		return addedOn;
	}
	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}
	
	public void clear()
	{
		this.id="";
		this.name="";
		this.addedOn="";
		this.address="";
		this.description="";
		this.email="";
		this.mobile="";
	}
	
}
