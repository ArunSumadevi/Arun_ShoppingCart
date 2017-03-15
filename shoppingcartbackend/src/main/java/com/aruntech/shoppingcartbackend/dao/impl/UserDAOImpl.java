package com.aruntech.shoppingcartbackend.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aruntech.shoppingcartbackend.dao.UserDAO;
import com.aruntech.shoppingcartbackend.model.User;
@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired

	  SessionFactory sessionFactory;

	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	
	public UserDAOImpl(SessionFactory sessionFactory)
	   {
		   this.sessionFactory=sessionFactory;
	   }


	@Transactional
	public String save(User user) 
		{
			log.debug("User Save function activated");
			try 
				{
					if(get(user.getId()) !=null)
						{
							log.debug("User Save function :ID Error");
							return "idError";
						}
					if(getUserEmail(user.getEmail()) !=null)
						{
							return "emailError";
						}
					sessionFactory.getCurrentSession().save(user);
					log.debug("User Save function successfull");
					return "success";
				} 
			
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("User save function exception "+e.getMessage());
					return e.getMessage();
				}	
		}
		
	//**********************************************Update data on DB******************************************************

	@Transactional
	public String update(User user) 
		{
			log.debug("User update function activated");
			try 
				{	
					if(get(user.getId()) ==null)
						{
						log.debug("User update function :ID Error");
							return "idError";	
						}
					user=(User) sessionFactory.getCurrentSession().merge(user);
					sessionFactory.getCurrentSession().update(user);
					log.debug("User update function successfull");
					return "success";
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("User update function exception "+e.getMessage());
					return e.getMessage();
				}
		}
		
	//**********************************************Delete data from DB****************************************************

	@Transactional
	public boolean delete(User user) 
		{
			log.debug("User delete function activated");
			try 
				{
					if(get(user.getId()) ==null)
						{
						log.debug("User delete function :ID Error");
							return false;
						}
					user=(User) sessionFactory.getCurrentSession().merge(user);
					sessionFactory.getCurrentSession().delete(user);
					log.debug("User delete function successfull");
					return true;
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("User delete function exception "+e.getMessage());
					return false;
				}
		}
		
	//**********************************************Get single data from DB************************************************

	@Transactional
	public User get(String ID) 
		{
			log.debug("User get function activated");
			return (User) sessionFactory.getCurrentSession().get(User.class, ID);
		}
		
	//**********************************************Get all data from DB***************************************************

	@Transactional
	public List<User> getAll() 
		{
			log.debug("User getall function activated");
			return sessionFactory.getCurrentSession().createQuery("from User order by firstname asc").list();
		}
	
	
	//**********************************************Get all data from DB excluding one***************************************************

	@Transactional
	public List<User> getUsers(String exclude) 
		{
			log.debug("User getUser function activated");	
			return sessionFactory.getCurrentSession().createQuery("from User where email <>'"+exclude+"'order by firstname asc").list();
		}

	//**********************************************Get user from DB based on email****************************************

	@Transactional
	public User getUserEmail(String userEmailId) 
		{
			log.debug("User getUserEmail function activated");
			String hql ="from User where Email='"+userEmailId+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			if(query.list().isEmpty())
				{
					log.debug("User getUserEmail function :user not found");
					return null;
				}
			else
				{
					log.debug("User getUserEmail function returned user");
					return (User) query.list().get(0);
				}
		}

	}	//**********************************************Class End**************************************************************
