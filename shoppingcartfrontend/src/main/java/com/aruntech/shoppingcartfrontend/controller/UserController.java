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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.CategoryDAO;
import com.aruntech.shoppingcartbackend.dao.OrderTableDAO;
import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.dao.ShoppingCartDAO;
import com.aruntech.shoppingcartbackend.dao.SupplierDAO;
import com.aruntech.shoppingcartbackend.dao.UserDAO;
import com.aruntech.shoppingcartbackend.model.Category;
import com.aruntech.shoppingcartbackend.model.OrderTable;
import com.aruntech.shoppingcartbackend.model.ShoppingCart;
import com.aruntech.shoppingcartbackend.model.Supplier;
import com.aruntech.shoppingcartbackend.model.User;
import com.aruntech.shoppingcartfrontend.model.Password;


@Controller
public class UserController
{
	

	@Autowired
	UserDAO userDAO;
	@Autowired
	User user;
	@Autowired
	User tempUser;
	@Autowired
	Category category;

	@Autowired
	CategoryDAO categoryDAO;	

	@Autowired
	Supplier supplier;

	@Autowired
	SupplierDAO supplierDAO;
	
	@Autowired
	OrderTable orderTable;
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	ShoppingCart shoppingCart;
	@Autowired
	ShoppingCartDAO shoppingCartDAO;
	
	@Autowired
	OrderTableDAO orderTableDAO;
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
//*********************************************** User clicked Signup link in menu  *******************************************************
	@RequestMapping("/NewUser") 
	public ModelAndView NewUser()
		{
			log.debug("SignUp link activated.");
			ModelAndView model=new ModelAndView("Index","user",user);
			model.addObject("Param","Register");
			log.debug("SignUp : Returned view to Index");
			return model;	
		}

//*********************************************** User clicked my profile link in menu  ***************************************************
	@RequestMapping("/user_Profile") 
	public ModelAndView userProfile()
		{
			log.debug("User Profile link activated.");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","userProfile");
			model.addObject("editEnable",false);
			log.debug("User Profile: Returned view to Index.");
			return model;	
		}

	
//*********************************************** User clicked edit button in menu  *******************************************************
	@RequestMapping("/user_editProfile") 
	public ModelAndView editProfile()
		{
			log.debug("Edit user profile link activated.");
			ModelAndView model=new ModelAndView("Index","user", user);
			model.addObject("Param","userProfile");
			model.addObject("editEnable",true);
			log.debug("Edit user profile: Returned  view to Index");
			return model;	
		}
		
//*********************************************** User clicked change password in menu  ***************************************************
	@RequestMapping("/user_ChangePassword") 
	public ModelAndView userChangePassword()
		{
			log.debug("Change password link activated.");
			ModelAndView model=new ModelAndView("Index","password", new Password());
			model.addObject("Param","userPassword");
			log.debug("Change Password : Returned view to Index.");
			return model;	
		}
		
//*********************************************** User clicked login link in menu *********************************************************
	@RequestMapping("/Login") 
	public ModelAndView user(HttpSession httpSession)
		{
			log.debug("Login link activated.");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","Login");
			log.debug("Login: Returned view to Index.");
			return model;	
		}
	
//*********************************************** Login error from login page *********************************************************
	@RequestMapping("/LoginError") 
	public ModelAndView LoginError(HttpSession httpSession)
		{
			log.debug("Login error link activated.");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","Login");
			model.addObject("errorMessage","Invalid Username or Password. Please try again..");
			log.debug("Login error: Returned view to Index.");
			return model;	
		}
		
//*********************************************** User clicked forgot password button in login page ***************************************
	@RequestMapping("/ForgotPass") 
	public ModelAndView ForgotPass()
		{
			log.debug("Forgot password link activated.");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","ForgotPass");
			log.debug("Forgot password: Returned view to Index.");
			return model;	
		}

//*********************************************** User clicked signOut link in user menu **************************************************
	@RequestMapping("/SignOut") 
	public String SignOut(HttpServletRequest request)
		{
			log.debug("Signout link activated.");
			HttpSession session=request.getSession(false);
			
			session.invalidate();
			session=request.getSession(true);
			session.setAttribute("sessionCategoryList", categoryDAO.getAll());
			session.setAttribute("sessionSupplierList", supplierDAO.getAll());
			session.setAttribute("sessionImageFolder", "/Resources/Images/");
			session.setAttribute("sessionCartCount","0");
			log.debug("Signout : forwarded to Landing Page.");
			return "forward:/";
	  }

		
//*********************************************** User clicked signOut link in user menu **************************************************
	@RequestMapping("/user_trackMyOrders") 
	public ModelAndView trackMyOrders(HttpServletRequest request)
		{
			log.debug("My Orders link activated.");
			
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","OrderTracking");
			log.debug("My Orders: Returned view to Index.");
			return model;
		}


//*********************************************** User clicked Register button in Register page *******************************************
	@RequestMapping(value="/UserReg",method = RequestMethod.POST ) 
	public ModelAndView Register(@ModelAttribute("user") User user)
		{
			log.debug("User Registration function activated.");
			ModelAndView model;
			System.out.println(user.getPassword()+"  "+user.getCnfPassword());
			if(! user.getPassword().equals(user.getCnfPassword()))
			{
				model=new ModelAndView("Index","user",user);
				model.addObject("Param","Register");
				model.addObject("errorMessage","Password and Confirm password mismatch. Please try again..");
				log.debug("User Registration : Password mismatch.");
				return model;
			}
			user.setId("USR-"+dateTime()+Integer.toString(randNum()));
			user.setEmail(user.getEmail().toLowerCase().trim());
			user.setRole("ROLE_USER");
			String userSaveResult=userDAO.save(user);
			if(userSaveResult.equals("success"))
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","Login");
					model.addObject("successMessage","User \""+  user.getEmail() +"\" registered successfully. Please login");
					log.debug("User Registration: success.");
				}
			else if(userSaveResult.equals("idError")) 
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","Register");
					model.addObject("errorMessage","User registration failed. Network error. please try again.");
					log.debug("User Registration : Id Error.");
				}
			else if(userSaveResult.equals("emailError")) 
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","Register");
					model.addObject("errorMessage","User registration failed.User email already registered.");
					log.debug("User Registration: Email Error.");
				}
			else
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","Register");
					model.addObject("errorMessage","User registration failed. unknown Error.");
					log.debug("User Registration :Exception Raised. Exception :"+userSaveResult);
				}
			log.debug("User Registration :Returned view to Index");
			return model;
		}

//*********************************************** User clicked update user in user profile page *******************************************
	@RequestMapping(value="/user_updateUser",method = RequestMethod.POST ) 
	public ModelAndView updateUser(@ModelAttribute("user") User user,HttpServletRequest request)
		{
			log.debug("Update user profile function activated.");
			ModelAndView model;
			tempUser=userDAO.getUserEmail(user.getEmail());
			user.setId(tempUser.getId());
			user.setGender(tempUser.getGender());
			user.setRole(tempUser.getRole());
			user.setPassword(tempUser.getPassword());
			String userUpdateResult=userDAO.update(user);
			if(userUpdateResult.equals("success"))
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","userProfile");
					model.addObject("editEnable",false);
					HttpSession session=request.getSession(false);
					session.setAttribute("sessionUser",userDAO.getUserEmail(user.getEmail()));
					model.addObject("successMessage","User \""+  user.getEmail() +"\" updated successfully.");
					log.debug("Update user profile function : Success.");
				}
			else if(userUpdateResult.equals("idError")) 
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","userProfile");
					model.addObject("editEnable",false);
					model.addObject("errorMessage","User update failed. User ID not found.");
					log.debug("Update user profile function : User ID not found.");
				}
			else
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","userProfile");
					model.addObject("editEnable",false);
					model.addObject("errorMessage","User update failed. unknown Error.");
					log.debug("Update user profile function : Exception raised. Exception : "+userUpdateResult);
				}
			log.debug("Update user profile function : Returned view to Index");
			return model;
		}


//*********************************************** User clicked change Password button  in change password page ****************************
	@RequestMapping(value="/user_changePassword",method = RequestMethod.POST ) 
	public ModelAndView changePassword(@ModelAttribute("password") Password password,HttpServletRequest request)
		{
			log.debug("Change Password function activated");
			ModelAndView model;
			
			user=userDAO.getUserEmail(password.getEmail());
			System.out.println(user);
			if(! password.getPassword().equals(user.getPassword()))
				{
					model=new ModelAndView("Index","password",password);
					model.addObject("Param","userPassword");
					model.addObject("errorMessage","Current password invalid. Please try again..");
					log.debug("Change Password function : Invalid current password");
					return model;	
				}
			if(! password.getNewPassword().equals(password.getCnfPassword()))
				{
					model=new ModelAndView("Index","password",password);
					model.addObject("Param","userPassword");
					model.addObject("errorMessage","New password & confirm password mismatch. Please try again..");
					log.debug("Change Password function : Confirm password mismatch");
					return model;	
				}
			System.out.println(password.getPassword());
			user.setPassword(password.getNewPassword());
			user.setMarked(false);
			String userPasswordResult=userDAO.update(user);
			if(userPasswordResult.equals("success"))
				{
					model=new ModelAndView("Index","user",user);
					model.addObject("Param","Login");
					model.addObject("successMessage","Password updated successfully. please login again..");
					HttpSession session=request.getSession(false);
					session.invalidate();
					session=request.getSession(true);
					session.setAttribute("sessionCategoryList", categoryDAO.getAll());
					session.setAttribute("sessionSupplierList", supplierDAO.getAll());
					session.setAttribute("sessionCartCount","0");
					log.debug("Change Password function : Success");
					return model;
				}
			else
				{
					model=new ModelAndView("Index","password",password);
					model.addObject("Param","userPassword");
					model.addObject("errorMessage","Password update failed. unknown Error.");
					log.debug("Change Password function : Exception Raised. Exception : "+userPasswordResult);
				}
			log.debug("Change Password function : Returned view to Index");
			return model;
		}

//*********************************************** User clicked login button in login page *************************************************
	@RequestMapping("/SignIn")  
	public ModelAndView SignIn(HttpServletRequest request)
		{ 
		
			log.debug("Signin function activated");
			ModelAndView model;
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String name = auth.getName();
			user=userDAO.getUserEmail(name);
			if(user.isMarked())
			{
				model=new ModelAndView("Index","password", new Password());
				model.addObject("Param","userPassword");
				model.addObject("errorMessage","Your account is marked due to password reset. Please change password to use your account.");
			}
			else
			{
			model=new ModelAndView("Index");
			model.addObject("Param","ActionResponce");
			model.addObject("successMessage","Successfully logged as "+ user.getFirstName() +" "+user.getLastName());
			}
			HttpSession session=request.getSession(true);
			session.setAttribute("sessionUser",user);
			session.setAttribute("sessionUserId", user.getId());
			session.setAttribute("sessionCartCount",shoppingCartDAO.getCount(user.getId()));
			log.debug("Signin function: success");
			return model;	
		}

//*********************************************** functions to get datetime combination ***************************************************

	private String dateTime ()
		{
			DateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
			Date date = new Date();
			return dateFormat.format(date);
		}

	private int randNum()
		{
			Random rn = new Random();
			return Math.abs(rn.nextInt() % 1000);
		}
	
}//***********************************************End **************************************************************************************	