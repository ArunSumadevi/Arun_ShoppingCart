package com.aruntech.shoppingcartfrontend.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aruntech.shoppingcartbackend.dao.UserDAO;
import com.aruntech.shoppingcartbackend.model.User;

@Controller
public class EmailController 

{

//***********************************************Autowiring from Backend*****************************************************************	
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
    private User user;
	
	@Autowired
    private UserDAO userDAO;
	
	private static Logger log = LoggerFactory.getLogger(EmailController.class);
	
//*********************************************** User clicked reset password link in forgot password page*******************************
	@RequestMapping("/resetPassword") 
	public String resetPassword(HttpServletRequest request, @RequestParam("forgot_pass_username")String forgot_pass_email, Model model)
		{
			log.debug("Reset password function activated");
			user=userDAO.getUserEmail(forgot_pass_email.toLowerCase().trim());
			if (user==null)
				{
					model.addAttribute("errorMessage", "Invalid email address");
					model.addAttribute("Param","ForgotPass");
					log.debug("Reset password :Invalid email id");
					return "Index";
				}
			log.debug("Reset password: calling fuction to send Reset email");
			sendResetEmail(user);
			model.addAttribute("successMessage", "An email containing the reset password is sent to your registered email.");
			model.addAttribute("Param","ActionResponce");
			log.debug("Reset password: Returning view to index");
			return "Index";
		  }

//*********************************************** function to send password reset password***********************************************
	public void sendResetEmail(User user)
		{
			log.debug("sendResetEmail function activated");
			String recipientAddress = user.getEmail();
	        String subject = "Password reset for e-Baazar.com";
	        String tempPass=randalphanumeric();
	        String message = "Hello, A password reset request was received from your side for e-Baazar.com login. As of now your account is marked and password is reset. Your new login password is "
	              + tempPass +" . Please login and set a new password for your account. Regards e_Baazar team.";
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message);
	        mailSender.send(email);
	        user.setPassword(tempPass);
	        user.setMarked(true);
	        userDAO.update(user);
	        log.debug("sendResetEmail function finished");
	    }

//*********************************************** function to genetate temporary  password***********************************************

	private String randalphanumeric()
		{
		 	SecureRandom rand = new SecureRandom();
		 	return new BigInteger(130, rand).toString(32);
		}

}//*********************************************** End of Class ****************************************************************************