package com.aruntech.shoppingcartfrontend.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.CategoryDAO;
import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.dao.SupplierDAO;
import com.aruntech.shoppingcartbackend.dao.UserDAO;
import com.aruntech.shoppingcartbackend.model.Category;
import com.aruntech.shoppingcartbackend.model.Product;
import com.aruntech.shoppingcartbackend.model.Supplier;
import com.aruntech.shoppingcartbackend.model.User;

@Controller
public class AdminController
{

	
//***********************************************Autowiring from Backend*****************************************************************	
	@Autowired
	Category category;
	
	@Autowired
	CategoryDAO categoryDAO;	
	
	@Autowired
	Supplier supplier;
	
	@Autowired
	SupplierDAO supplierDAO;

	@Autowired
	Product product;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User user;
	
	private static Logger log = LoggerFactory.getLogger(AdminController.class);
		
		
//***********************************************Admin clicked add new category link in admin menu*****************************************
	@RequestMapping("/Admin_newCategory")
	public ModelAndView newCategory()
		{
			log.debug("Add new category link  activated");
			category.clear();
			ModelAndView model=new ModelAndView("Index","category",category);
			model.addObject("Action","Admin_addNewCategory");
			model.addObject("Param","adminCategory");
			log.debug("Add new category ->Returned view to Index");
			return model;
		}
				
//***********************************************Admin clicked add new supplier link in admin menu*****************************************
	@RequestMapping("/Admin_newSupplier")
	public ModelAndView newSupplier()
		{
			log.debug("Add new supplier link activated");
			supplier.clear();
			ModelAndView model=new ModelAndView("Index","supplier",supplier);
			model.addObject("Param","adminSupplier");
			model.addObject("Action","Admin_addNewSupplier");
			model.addObject("AddDate",myDate());
			log.debug("Add new supplier ->Returned view to Index");
			return model;
		}

//***********************************************Admin clicked add new Product link in admin menu******************************************
	@RequestMapping("/Admin_newProduct")
	public ModelAndView addNewProduct()
		{
			log.debug("Add new product link activated");
			product.clear();
			ModelAndView model=new ModelAndView("Index","product",product);
			model.addObject("Param","adminProduct");
			model.addObject("Action","Admin_addNewProduct");
			model.addObject("AddDate",myDate());
			log.debug("Add new product ->Returned view to Index");
			return model;
		}


//***********************************************Admin clicked update supplier button in the view supplier page****************************
	@RequestMapping("/Admin_modifySupplier")
	public ModelAndView modifySupplier(@RequestParam("id") String id)
		{
			log.debug("Modify supplier link activated");
			supplier=supplierDAO.get(id);
			ModelAndView model=new ModelAndView("Index","supplier",supplier);
			model.addObject("Param","adminSupplier");
			model.addObject("Action","Admin_updateSupplier");
			model.addObject("AddDate",supplier.getAddedOn());
			log.debug("Modify supplier->Returned view to Index");
			return model;
		}

	
//***********************************************Admin clicked Manage Admin link in admin menu*********************************************
	@RequestMapping(value="/Admin_adminManage")
	public ModelAndView Admin_adminManage(HttpServletRequest request)
		{
			log.debug("Manage Admin link activated");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("userList",userDAO.getAll());
			model.addObject("Param","adminUser");
			log.debug("Manage Admin ->Returned view to Index");
			return model;
		}
	
			
//***********************************************Admin clicked delete user in manage admin page **********************************
	@RequestMapping("/Admin_deleteUser")
	public String deleteUser(@RequestParam("id") String id, Model model)
		{
			log.debug("Delete user function activated");
			user=userDAO.get(id);
			if(userDAO.delete(user))
				{
					
					log.debug("Delete user function successfull");
				}
			else
				log.debug("Delete user function failed");
			log.debug("Delete user ->forwarded to manageAdmin");
			return "forward:/Admin_adminManage";
		}
		
//***********************************************Admin clicked modify user in manage admin page **********************************
	@RequestMapping("/Admin_modifyUser")
	public ModelAndView modifyUser(@RequestParam("userId") String id,@RequestParam("userRole") String userRole)
		{
			log.debug("Update user Role function activated");
			ModelAndView model=new ModelAndView("Index");
			user=userDAO.get(id);
			user.setRole(userRole);
			String userUpdateResult=userDAO.update(user);
			if(userUpdateResult.equals("success"))
			{
				model.addObject("message","User updated successfully");
				log.debug("Update user Role function : Success.");
			}
			else if(userUpdateResult.equals("idError")) 
			{
				model.addObject("message","User update failed. User not found");
				log.debug("Update user Role function : User ID not found.");
			}
			else
			{
				model.addObject("message","User update failed. unknown Error.");
			
				log.debug("Update user Role function : Exception raised. Exception : "+userUpdateResult);
			}
			model.addObject("userList",userDAO.getAll());
			model.addObject("Param","adminUser");
			log.debug("Update user Role function : Returned view to Index");
			return model;
		}
				
//***********************************************request from angularJs in the manage admin page to get all users************************	
	@RequestMapping(value = "/Admin_getAllUser", method = RequestMethod.GET)
    public @ResponseBody List<User> listAllUsers() 
		{
			log.debug("Get All User function activated");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			log.debug("Get All User function Returned");
			return userDAO.getUsers(name);
		}


//***********************************************get todays date***************************************************************************
	private String myDate ()
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
			Date date = new Date();
			return dateFormat.format(date);
		}

} //*********************************************** End of Class ****************************************************************************