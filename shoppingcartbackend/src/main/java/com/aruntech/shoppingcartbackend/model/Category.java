package com.aruntech.shoppingcartbackend.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity

@Table(name="category")

@Component
public class Category implements Serializable
{
	
	@Id
	private String id;
	private String name;
	private String description;
	
	private static final long serialVersionUID = 4657462015039726043L;
	@Transient
	private MultipartFile image;
	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	public Set<Product> products;
	
	//**********************************************Getters & Setters******************************************************
	
	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public Set<Product> getProducts() 
	{
		return products;
	}
	
	public void setProducts(Set<Product> products) 
	{
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void clear()
	{
		this.id="";
		this.name="";
		this.description="";
	}
}	//**********************************************Class End**************************************************************