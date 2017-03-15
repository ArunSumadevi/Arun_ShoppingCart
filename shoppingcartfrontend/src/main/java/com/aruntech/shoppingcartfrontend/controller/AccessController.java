package com.aruntech.shoppingcartfrontend.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aruntech.shoppingcartbackend.dao.UserDAO;
import com.aruntech.shoppingcartbackend.model.User;

@Controller
public class AccessController 
{
	@Autowired
	User myUser;
	@Autowired
	UserDAO userDAO;
	private static Logger log = LoggerFactory.getLogger(UserController.class);

	//***********************************************Access Denied*********************************************
	@RequestMapping("/AccessDenied")
	public ModelAndView accessDenied(Principal user)
		{
			log.debug("Access Denied");
			ModelAndView model=new ModelAndView("Index");
			model.addObject("Param","AccessDenied");
			if(user!=null)
				{
					myUser=userDAO.getUserEmail(user.getName());
					model.addObject("message","Hello " + myUser.getFirstName() +" "+ myUser.getLastName() +", you are not allowd to access this page. This page requires more credentials.");
				}
			else
				model.addObject("message","Hello user , you are not allowd to access this page. This page requires more credentials.");
			log.debug("Access Control ->Returned view to Index");
			return model;
		}
}
