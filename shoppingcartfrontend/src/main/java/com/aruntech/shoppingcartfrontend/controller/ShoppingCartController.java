package com.aruntech.shoppingcartfrontend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.dao.ShoppingCartDAO;
import com.aruntech.shoppingcartbackend.model.Product;
import com.aruntech.shoppingcartbackend.model.ShoppingCart;
import com.aruntech.shoppingcartbackend.model.User;

@Controller
public class ShoppingCartController {

	
//***********************************************Autowiring from Backend*****************************************************************	

	@Autowired
	ShoppingCart shoppingCart;
	@Autowired
	ShoppingCartDAO shoppingCartDAO;
	@Autowired
	User user;
	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;
	
	
	private static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);	
	
//*********************************************** Admin clicked add to cart button in product page*****************************************
	@RequestMapping("/user_productAddCart")
	public ModelAndView productAddCart(@RequestParam("id") String productId,HttpServletRequest request)
		{
			log.debug("Add product to cart function activated");
			ModelAndView model= new ModelAndView("Index");
			shoppingCart.setId("SCRT-"+dateTime()+Integer.toString(randNum()));
			shoppingCart.setProductId(productId);
			HttpSession session=request.getSession(false);
			user=(User) session.getAttribute("sessionUser");
			shoppingCart.setUserId(user.getId());
			shoppingCart.setQuantity(1);
			shoppingCart.setAddedOn(getDate());
			String sCartSaveResult=shoppingCartDAO.save(shoppingCart);
			if(sCartSaveResult.equals("idError"))
				{
					model.addObject("product",productDAO.get(productId));
					model.addObject("Param","productDisplay");
					model.addObject("errorMessage","Error adding product to shopping cart. Please try again..");
					log.debug("Add product to cart : Id error");
					return model;
				}
			else if(sCartSaveResult.equals("productPresent"))
				{
					model.addObject("product",productDAO.get(productId));
					model.addObject("Param","productDisplay");
					model.addObject("errorMessage","Product is already in the shopping cart.. ");
					log.debug("Add product to cart : Product present.");
					return model;
				}
			else if(sCartSaveResult.equals("success"))
				{
					model.addObject("product",productDAO.get(productId));
					model.addObject("Param","productDisplay");
					int count=Integer.valueOf(session.getAttribute("sessionCartCount").toString());
					session.setAttribute("sessionCartCount", (count+=1));
					model.addObject("successMessage","Product added to shopping cart.. ");
					log.debug("Add product to cart function success");
					return model;
				}
			return model;
		}		
		
//*********************************************** Admin clicked delete cart link********************************************************
	@RequestMapping("/user_deleteCart")
	public String deleteCart(@RequestParam("userId") String userId,@RequestParam("cartId") String cartId,HttpServletRequest request)
		{
			log.debug("Delete cart function activated");
			shoppingCart.setId(cartId);
			if(shoppingCartDAO.delete(shoppingCart))
			{
				HttpSession session= request.getSession(false);
				int count=(Integer) session.getAttribute("sessionCartCount");
				session.setAttribute("sessionCartCount", count-1);
			}
			log.debug("Delete cart function: forwarding to show cart");
			return "forward:/user_myCartShow?id="+userId;
		}

//*********************************************** Admin clicked update cart in shopping cart page******************************************
	@RequestMapping("/user_modifyCart")
	public String modifyCart(@RequestParam("cartId") String cartId,@RequestParam("productId") String productId,@RequestParam("userId") String userId,@RequestParam("addedOn") String addedOn,@RequestParam("cartQuantity") int cartQuantity,HttpServletRequest request)
		{
			log.debug("Modify cart function activated");
			shoppingCart.setAddedOn(addedOn);
			shoppingCart.setId(cartId);
			shoppingCart.setProductId(productId);
			shoppingCart.setQuantity(cartQuantity);
			shoppingCart.setUserId(userId);
			shoppingCartDAO.update(shoppingCart);
			log.debug("Modify cart function: forwarding to show cart");
			return "forward:/user_myCartShow?id="+userId;
		}
		
//*********************************************** user clicked shopping cart link**********************************************************
	@RequestMapping("/user_myCartShow")
	public ModelAndView myCartShow(@RequestParam("id") String id)
		{
			log.debug("Show cart function activated");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param", "myCartShow");
			model.addObject("shoppingCart",shoppingCartDAO.getUserCart(id));
			log.debug("Modify cart function: Returning view to Index");
			return model;
		}
//***********************************************get dateTime******************************************************************************
	private String dateTime ()
		{
			DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
			Date date = new Date();
			return dateFormat.format(date);
		}

//***********************************************get todays date******************************************************************************
	private String getDate()
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			Date date = new Date();
			return dateFormat.format(date);
		}
	
	//***********************************************get random number******************************************************************************
	
	private int randNum()
		{
			Random rn = new Random();
			return Math.abs(rn.nextInt() % 1000);
		}

}//********************************************** END *************************************************************************************

