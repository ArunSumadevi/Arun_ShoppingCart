package com.aruntech.shoppingcartbackend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="product")
@Component
public class Product implements Serializable{
	
	@Id
	private String id;
	private String name;
	private String description;
	private double price;
	private String categoryId;
	private String supplierId;
	private int stock;
	private String addedOn;
	@Transient
	private static final long serialVersionUID = 4657462015039726033L;
	@Transient
	private MultipartFile image;
	
	@ManyToOne
	@JoinColumn(name="categoryId", updatable=false,insertable=false, nullable=false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="supplierId", updatable=false,insertable=false, nullable=false)
	private Supplier supplier;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategoryId() {
		return categoryId;
	}
	

	public String getSupplierId() {
		return supplierId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
public void clear()
{
	this.addedOn="";
	this.categoryId="";
	this.description="";
	this.id="";
	this.name="";
	this.price=0.0;
	this.stock=0;
	this.supplierId="";
}

public void setCategoryId(String categoryId) {
	
		this.categoryId = categoryId;
}

public void setSupplierId(String supplierId) {
	
		this.supplierId = supplierId;
}
	
}
