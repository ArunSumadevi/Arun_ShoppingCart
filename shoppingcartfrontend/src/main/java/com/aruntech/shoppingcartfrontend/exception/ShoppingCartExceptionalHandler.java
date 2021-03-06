package com.aruntech.shoppingcartfrontend.exception;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.hql.internal.ast.QuerySyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
@ControllerAdvice
public class ShoppingCartExceptionalHandler
{
	public class ResourceNotFoundException extends RuntimeException { };
private static Logger log = LoggerFactory.getLogger(ShoppingCartExceptionalHandler.class);
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(HttpServletRequest request, Exception e)
		{
			log.error("SQL Exception Occured :: URL = "+request.getRequestURL());
			log.error("SQL Exception Occured:: Exception=" +e.getMessage());
			ModelAndView model = new ModelAndView("./Exception/exception");
			model.addObject("message" , "It seems that one of the tabel Or few feilds does not exist in DB, See the table and feilds created");
			model.addObject("errorMessage", e.getMessage());
			return model;
		}
	
	@ExceptionHandler(CannotCreateTransactionException.class)
	public ModelAndView dbServerNotStarted(HttpServletRequest request, Exception e)
		{
			log.error("SQL Exception Occured :: URL = "+request.getRequestURL());
			log.error("SQL Exception Occured:: Exception=" +e.getMessage());
			ModelAndView model = new ModelAndView("./Exception/exception");
			model.addObject("message" , " It seems that Database Sever is not started");
			model.addObject("errorMessage", e.getMessage());
			return model;
		}
	
	@ExceptionHandler(QuerySyntaxException.class)
	public ModelAndView handleQuerySyntaxException(HttpServletRequest request, Exception e)
		{
			log.error("Query Syntax Exception Occured :: URL = "+request.getRequestURL());
			log.error("Query Syntax Exception Occured:: Exception=" +e.getMessage());
			ModelAndView model = new ModelAndView("./Exception/exception");
			model.addObject("message" , " It seems that one of the query is not proper,  the logger for more information");
			model.addObject("errorMessage", e.getMessage());
			return model;
		}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandlerException(HttpServletRequest request, Exception e)
		{
			log.error("NoHandlerException Occured :: URL = "+request.getRequestURL());
			log.error("NoHandlerException Occured:: Exception=" +e.getMessage());
			ModelAndView model = new ModelAndView("./Exception/exception");
			model.addObject("message" , "No Handler found Invalid URL See the logger for more information");
			model.addObject("errorMessage", e.getMessage());
			return model;
		}
	
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleIOException(HttpServletRequest request, Exception e)
		{
			log.error("IOException handler executed");
			log.error("IOException Occured:: Exception=" +e.getMessage());
			ModelAndView model = new ModelAndView("./Exception/exception");
			model.addObject("message" , "Not able to connect to the server , Please contact administrator");
			model.addObject("errorMessage", e.getMessage());
			return model;
		}
	
}
