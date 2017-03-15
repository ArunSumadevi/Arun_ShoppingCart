package com.aruntech.shoppingcartbackend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Entity
@Table(name="shoppingcart")
@Component
public class ShoppingCart implements Serializable{

	@Id
	private String id;
	private String userId;
	private String productId;
	private int quantity;
	private String addedOn;
	
	@Transient
	private String Comment;
	@Transient
	private static final long serialVersionUID = 4657462015039726040L;
	@ManyToOne
	@JoinColumn(name="productId", updatable=false,insertable=false, nullable=false)
	private Product product;
	
	

	@ManyToOne
	@JoinColumn(name="userId", updatable=false,insertable=false, nullable=false)
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	//**********************************************Getters & Setters******************************************************
	

}


