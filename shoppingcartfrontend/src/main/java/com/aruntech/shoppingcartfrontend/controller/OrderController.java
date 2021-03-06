package com.aruntech.shoppingcartfrontend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.OrderTableDAO;
import com.aruntech.shoppingcartbackend.dao.ShoppingCartDAO;
import com.aruntech.shoppingcartbackend.model.OrderTable;
import com.aruntech.shoppingcartbackend.model.ShoppingCart;
import com.aruntech.shoppingcartbackend.model.User;

@Controller
public class OrderController 
{

//***********************************************Autowiring from Backend*****************************************************************	

	@Autowired
	User user;
	
	@Autowired
	OrderTableDAO orderTableDAO;
	
	@Autowired
	OrderTable orderTable;
	
	@Autowired
	ShoppingCart shoppingCart;
	
	@Autowired
	ShoppingCartDAO shoppingCartDAO;
	
	private static Logger log = LoggerFactory.getLogger(OrderController.class);
	
//*********************************************** User clicked Track button in product display page  ***********************************
	@RequestMapping("/user_TrackOrder") 
	public ModelAndView  TrackOrder(HttpServletRequest request)
		{
			log.debug("TrackOrder function activated");
			ModelAndView model = new ModelAndView("Index");
			HttpSession session =request.getSession(false);
			user=(User) session.getAttribute("sessionUser");
			List<OrderTable> tempList=orderTableDAO.getUserOrder(user.getId());
			List<OrderTable> list=new ArrayList<OrderTable>();
			if(tempList.isEmpty())
				{
					model.addObject("errorMessage","No order details availabe for user.");
				}
			else
			{
				for(OrderTable x:tempList)
				{
					int count=0;
					for(OrderTable y:list)
					{
						if(y.getNumber().equals(x.getNumber()))
							count++;
					}
					if(count==0)
						list.add(x);
				}
			}
			model.addObject("orderList",list);
			model.addObject("trackingType","userId");
			model.addObject("Param","OrderDisplay");
			log.debug("TrackOrder ->Returned view to Index");
			return model;
		}
		
//*********************************************** User clicked Buy Now button in product display page  ***********************************
	@RequestMapping("/user_buyNow") 
	public String  checkout(@RequestParam("id") String productId,HttpServletRequest request)
		{
			log.debug("Buy Now function activated");
			shoppingCart.setId("SCRT-"+dateTime()+Integer.toString(randNum()));
			shoppingCart.setProductId(productId);
			HttpSession session= request.getSession(false);
			user=(User) session.getAttribute("sessionUser");
			session.setAttribute("sessionShoppingCart", shoppingCartDAO.getUserCart(user.getId()));
			shoppingCart.setUserId(user.getId());
			shoppingCart.setQuantity(1);
			shoppingCart.setAddedOn(getDate());
			String sCartSaveResult=shoppingCartDAO.save(shoppingCart);
			session.setAttribute("sessionShoppingCart", shoppingCartDAO.getUserCart(user.getId()));
			if (sCartSaveResult.equals("success"))
				{
					int count=Integer.valueOf(session.getAttribute("sessionCartCount").toString());
					session.setAttribute("sessionCartCount", (count+=1));
				}
			log.debug("Buy Now: Redirected to cartCheckout");
			return "redirect:/user_cartCheckOut?id="+user.getId();	
		}
		
//*********************************************** user clicked checkout button in cart page************************************************
	@RequestMapping("/user_cartCheckOut")
	public String cartCheckOut(@RequestParam("id") String userId,HttpServletRequest request)
		{
			log.debug("cartCheckout function activated");
			List<ShoppingCart> shoppingCartList=shoppingCartDAO.getUserCart(userId);
			for(ShoppingCart x: shoppingCartList)
				{
					if (x.getProduct().getStock()< x.getQuantity())
						{
							x.setQuantity(x.getProduct().getStock());
							shoppingCart=shoppingCartDAO.get(x.getId());
							shoppingCart.setQuantity(x.getQuantity());
							shoppingCartDAO.update(shoppingCart);	
							x.setComment("Qty reduced due to unavailability.");
						}
				}
			HttpSession session= request.getSession(false);
			session.setAttribute("sessionShoppingCart", shoppingCartList);
			log.debug("cartCheckout: Redirected to webflow");
			return "redirect:/user_cart_checkout";
		}
			
			
//*********************************************** user clicked order number in the my order page ****************************************	
	@RequestMapping("/user_displayOrder")
	public ModelAndView user_displayOrder(@RequestParam("id") String Id,HttpServletRequest request)
		{
			log.debug("Display Order function activated");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","orderDetails");
			model.addObject("orderNumber",Id);
			List<OrderTable> list= orderTableDAO.getByOrderNumber(Id);
			if(list.isEmpty())
				{
					model.addObject("errorMessage", "Order Number not fount in data base.");
					log.debug("Display Order : Order number not in data base");
				}
			model.addObject("orderList",list);
			log.debug("Display Order: Returning view to Index");
			return model;
		}
				
	
	
//*********************************************** user clicked receive button  in the order details page ****************************************	
@RequestMapping("/user_receiveOrder")
public String user_receiveOrder(@RequestParam("orderId") String orderId,HttpServletRequest request)
{
	log.debug("Receive Order function activated");
	orderTable=orderTableDAO.get(orderId);
	orderTable.setProductStatus("Received by user");
	orderTable.setUpdateDate(getDate());
	orderTableDAO.update(orderTable);
	List<OrderTable> list= orderTableDAO.getByOrderNumber(orderTable.getNumber());
	int count=orderTableDAO.getOrderStatus(orderTable.getNumber()).size();
	if(count==0)
	{
		for(OrderTable x:list)
		{
			x.setOrderStatus("Completed");
			orderTableDAO.update(x);
		}
	}
	else
	{
		for(OrderTable x:list)
		{
			x.setOrderStatus("Partialy delivered");
			orderTableDAO.update(x);
		}
	}
	log.debug("Receive Order: Returning view to Index");
	return "redirect:/user_displayOrder?id="+orderTable.getNumber();
}
		
	
//*********************************************** get date&time****************************************************************************
	private String dateTime ()
		{
			DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
			Date date = new Date();
			return dateFormat.format(date);
		}

//*********************************************** get date*********************************************************************************
	private String getDate()
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			Date date = new Date();
			return dateFormat.format(date);
		}
		

//*********************************************** generate 4 digit random number***********************************************************

		private int randNum()
		{
			Random rn = new Random();
			return Math.abs(rn.nextInt() % 1000);
		}
		
}//********************************************** END *************************************************************************************                            