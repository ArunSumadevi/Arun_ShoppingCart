package com.aruntech.shoppingcartfrontend.webflow;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aruntech.shoppingcartbackend.dao.OrderTableDAO;
import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.dao.ShoppingCartDAO;
import com.aruntech.shoppingcartbackend.model.OrderTable;
import com.aruntech.shoppingcartbackend.model.Product;
import com.aruntech.shoppingcartbackend.model.ShoppingCart;
import com.aruntech.shoppingcartbackend.model.User;
import com.aruntech.shoppingcartfrontend.model.BillingAddress;
import com.aruntech.shoppingcartfrontend.model.PaymentMethod;
import com.aruntech.shoppingcartfrontend.model.ShippingAddress;
@Component
public class OrderWebFlow implements Serializable{
private static Logger log = LoggerFactory.getLogger(OrderWebFlow.class);
private static final long serialVersionUID = 4657462015039726034L;

	
	@Autowired
	private OrderTableDAO orderTableDAO;
	
	@Autowired
	ShoppingCartDAO shoppingCartDAO;
	@Autowired
	User user;
	
	@Autowired
	ShoppingCart shoppingCart;
	
	@Autowired
	OrderTable orderTable;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;
	
	public OrderTable initFlow()
		{
			log.debug("WEBFLOW->-> starting of the method initFlow");
			orderTable = new OrderTable();
			log.debug("Webflow->-> ending of the method initFlow");
			return orderTable;
		}
	
	
	public String addShippingAddress(OrderTable orderTable, ShippingAddress shippingAddress)
		{
			log.debug("WEBFLOW->-> starting of the method addShippingAddress");
			orderTable.setShippingAddress(shippingAddress.getAddress1()+", "+shippingAddress.getAddress2()+", "+shippingAddress.getCity()+", "+shippingAddress.getState()+", "+shippingAddress.getCountry()+", "+shippingAddress.getPincode());
			log.debug("Webflow->-> ending of the method addShippingAddress");
			return "success";
		}
	
	
	public String addBillingAddress(OrderTable orderTable, BillingAddress billingAddress)
		{
			log.debug("WEBFLOW->-> starting of the method addShippingAddress");
			orderTable.setBillingAddress(billingAddress.getAddress1()+", "+billingAddress.getAddress2()+", "+billingAddress.getCity()+", "+billingAddress.getState()+", "+billingAddress.getCountry()+", "+billingAddress.getPincode());
			log.debug("Webflow->-> ending of the method addShippingAddress");
			return "success";
		}
	
	public String addPaymentMethod(OrderTable orderTable, PaymentMethod paymentMethod)
		{
			log.debug("WEBFLOW->-> starting of the method addPaymentMethod");
			orderTable.setCardNumber(paymentMethod.getCardNumber());
			orderTable.setPaymentMode(paymentMethod.getPaymentMode());
			log.debug("Webflow->-> ending of the method addPaymentMethod");
			return "success";
		}
	
	public String placeOrder(OrderTable orderTable, User user)
		{
			log.debug("WEBFLOW->-> starting of the method addPaymentMethod");
			List<ShoppingCart> sCart=shoppingCartDAO.getUserCart(user.getId());
			String orderNumber=dateTime()+Integer.toString(OrderRandNum());
			int count=0;
			String billingAddress=orderTable.getBillingAddress();
			String shippingAddress=orderTable.getShippingAddress();
			String paymentMode=orderTable.getPaymentMode();
			for(ShoppingCart x: sCart)
				{
					if(x.getQuantity()==0)
						{
							shoppingCartDAO.delete(x);
							continue;
						}
					orderTable.clear();
					orderTable.setId("ORD-"+dateTime()+Integer.toString(randNum()));
					orderTable.setNumber(orderNumber);
					orderTable.setBillingAddress(billingAddress);
					orderTable.setDate(getDate());
					orderTable.setProductPrice(x.getProduct().getPrice());
					orderTable.setPaymentMode(paymentMode);
					orderTable.setProductName(x.getProduct().getName());
					orderTable.setQuantity(x.getQuantity());
					orderTable.setShippingAddress(shippingAddress);
					orderTable.setProductStatus("Order Created");
					orderTable.setSupplierName(x.getProduct().getSupplier().getName());
					orderTable.setUpdateDate(getDate());
					orderTable.setUserId(x.getUserId());
					orderTable.setOrderStatus("Order Created");
					if(orderTableDAO.save(orderTable))
						{
							shoppingCartDAO.delete(x);
							product=x.getProduct();
							product.setStock(product.getStock()-x.getQuantity());
							productDAO.update(product);
						}
					else
						{
							count++;
						}
				}
			log.debug("Webflow->-> ending of the method addPaymentMethod");
			if(count!=0)
				return "false";
			else
				
			return "success";
				
		}	
	
//****************************************************************************************************
		private String dateTime ()
		{
			DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
			Date date = new Date();
			return dateFormat.format(date);
		}
		
		private String getDate()
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			Date date = new Date();
			return dateFormat.format(date);
		}
		private int OrderRandNum()
		{
			Random rn = new Random();
			return Math.abs(rn.nextInt() % 10000);
		}
		private int randNum()
		{
			Random rn = new Random();
			return Math.abs(rn.nextInt() % 10000);
		}
}
