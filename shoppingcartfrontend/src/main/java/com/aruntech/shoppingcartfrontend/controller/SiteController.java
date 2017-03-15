package com.aruntech.shoppingcartfrontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.CategoryDAO;
import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.dao.ShoppingCartDAO;
import com.aruntech.shoppingcartbackend.dao.SupplierDAO;
import com.aruntech.shoppingcartbackend.model.User;

@Controller
public class SiteController {
	
//***********************************************Autowiring from Backend*****************************************************************	
	@Autowired
	SupplierDAO supplierDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ShoppingCartDAO shoppingCartDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	User user;
	
	private static Logger log = LoggerFactory.getLogger(SiteController.class);	
//*********************************************** Landing Page*****************************************************************************
	@RequestMapping(value={"/Index","/"} ) 
	public ModelAndView myindex ( HttpServletRequest request)  
		{	
			log.debug("Landing page link activated.");
			ModelAndView model=new ModelAndView("Index");
			HttpSession session=request.getSession(true);
			session.setAttribute("sessionSupplierList", supplierDAO.getAll());
			session.setAttribute("sessionCategoryList", categoryDAO.getAll());
			if(session.getAttribute("sessionUser")==null)
				{
					session.setAttribute("sessionCartCount","0");
				}
			else
				{
					user=(User) session.getAttribute("sessionUser");
					session.setAttribute("sessionCartCount",shoppingCartDAO.getUserCart(user.getId()).size());
				}
			model.addObject("carouselList",productDAO.carouselList());
			model.addObject("Param","Home");
			log.debug("Landing page: Returned view to index.");
			return model;
		}
		
//*********************************************** User clicked contactus link in menu *****************************************************
	@RequestMapping("/ContactUs")   
	public ModelAndView ContactUs()
		{
			log.debug("Contact Us link activated.");
			ModelAndView model=new ModelAndView("Index");
			log.debug("Contact Us: Returned view to Index.");
			return model;	
		}
	
//*********************************************** User clicked AboutUs link in menu *******************************************************
	@RequestMapping("/AboutUs")   
	public ModelAndView AboutUs()
		{
			log.debug("About us link activated.");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","AboutUs");
			log.debug("About Us: Returned view to Index.");
			return model;	
		}
		
//*********************************************** User clicked search button in menu ******************************************************
	@RequestMapping("/Search")  
	public ModelAndView Search()
		{
			log.debug("Search link activated.");
			ModelAndView model=new ModelAndView("test");
			model.addObject("Param","ForgotPass");
			log.debug("Search: Returned view to Index.");
			return model;	
		}


//*********************************************** User clicked category link i productmenu ******************************************************
	@RequestMapping("/productShow")
	public ModelAndView productShow(@RequestParam("id") String Id)
		{
			log.debug("Show Product link activated.");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("categoryId",Id);
			model.addObject("Param","productShow");
			log.debug("Show Product : Returned View to Index..");
			return model;
		}
	
	
	
//***********************************************request from angularJs in the manage admin page to get all users************************	
	@RequestMapping(value = "/getAllProductName", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllProductNames() 
		{
			log.debug("Get All product name function activated");
			log.debug("Get All User function Returned");
			return productDAO.productNames();
		}



}//**********************************************END***************************************************************************************
