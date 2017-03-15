package com.aruntech.shoppingcartfrontend.model;

import java.io.Serializable;

public class PaymentMethod implements Serializable 
{

	private String paymentMode;
	private String cardNumber;
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		
	}

	private static final long serialVersionUID = 4657462015039726060L;
	public String getPaymentMode() 
		{
			return paymentMode;
		}

	public void setPaymentMode(String paymentMode)
		{
			this.paymentMode = paymentMode;
		}
}
