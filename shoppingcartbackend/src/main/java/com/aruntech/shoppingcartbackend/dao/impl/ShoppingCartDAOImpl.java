package com.aruntech.shoppingcartbackend.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aruntech.shoppingcartbackend.dao.ShoppingCartDAO;
import com.aruntech.shoppingcartbackend.model.ShoppingCart;

@Repository("shoppingCartDAO")
public class ShoppingCartDAOImpl implements ShoppingCartDAO , Serializable{
	@Autowired
	private SessionFactory sessionFactory;

	//**********************************************Constructor************************************************************
	private static Logger log = LoggerFactory.getLogger(ShoppingCartDAOImpl.class);
	public ShoppingCartDAOImpl()
		{
			
		}
	
	public ShoppingCartDAOImpl(SessionFactory sessionFactory)
		{
			
			this.sessionFactory = sessionFactory;
		}
	
	//**********************************************Save data to database**************************************************

	@Transactional
	public String save(ShoppingCart shoppingCart) 
		{
			log.debug("ShoppingCart Save function activated");
			try 
				{
					if (get(shoppingCart.getId()) != null)
						{
							log.debug("ShoppingCart Save function :ID error");
							return "idError";
						}
					else if (isPresent(shoppingCart.getUserId(),shoppingCart.getProductId())) 
						{
							log.debug("ShoppingCart Save function : Product already presesnt");
							return "productPresent";
						}
					else 
						{
							sessionFactory.getCurrentSession().save(shoppingCart);
							log.debug("ShoppingCart Save function successfull");
							return "success";
						} 
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("ShoppingCart Save function exception "+e.getMessage());
					return e.getMessage();
				}
		}
	
	//**********************************************Update data on DB******************************************************

	@Transactional
	public boolean update(ShoppingCart shoppingCart) 
		{
			log.debug("ShoppingCart update function activated");
			try 
				{
					if (get(shoppingCart.getId()) == null)
						{
							log.debug("ShoppingCart update function :ID error");
							return false;
						}
					else 
						{
							shoppingCart=(ShoppingCart) sessionFactory.getCurrentSession().merge(shoppingCart);
							sessionFactory.getCurrentSession().update(shoppingCart);
							log.debug("ShoppingCart update function successfull");
							return true;
						} 
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("ShoppingCart update function exception "+e.getMessage());
					return false;
				}
		}
	
	//**********************************************Delete data from DB****************************************************

	@Transactional
	public boolean delete(ShoppingCart shoppingCart) 
	{
		log.debug("ShoppingCart delete function activated");
		try 
		{
			if(get(shoppingCart.getId()) ==null)
			{
				log.debug("ShoppingCart delete function :ID error");
				return false;
			}
			shoppingCart=(ShoppingCart) sessionFactory.getCurrentSession().merge(shoppingCart);
			sessionFactory.getCurrentSession().delete(shoppingCart);
			log.debug("ShoppingCart delete function successfull");
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			log.debug("ShoppingCart delete function exception "+e.getMessage());
			return false;	
		}
	}
	
	//**********************************************Get single data from DB************************************************

	@Transactional
	public ShoppingCart get(String ID) 
		{
			log.debug("ShoppingCart get function activated");
			return (ShoppingCart) sessionFactory.getCurrentSession().get(ShoppingCart.class, ID);
		}
	
	//**********************************************Get all data from DB***************************************************

	@Transactional
	public List<ShoppingCart> getAll() 
		{
			log.debug("ShoppingCart getAll function activated");
			String hql ="from ShoppingCart";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("ShoppingCart getAll function finished");
			return query.list();
		}
	
	//**********************************************Get all data from DB based on user*************************************

	@Transactional
	public List<ShoppingCart> getUserCart(String userId) 
		{
			log.debug("ShoppingCart getUserCart function activated");
			String hql ="from ShoppingCart where userid='"+userId+"' order by id asc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("ShoppingCart getUserCart function activated");
			return query.list();
		}
	
	@Transactional
	public boolean isPresent(String userId,String productId) 
		{
		log.debug("ShoppingCart isPresent function activated");
			String hql ="from ShoppingCart where userid='"+userId+"' and productid='"+productId+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			if(query.list().isEmpty())
				{
					log.debug("ShoppingCart isPresent function :Empty");
					return false;
				}
			else
				{
					log.debug("ShoppingCart isPresent function :present");
					return true;
				}
		}
	
	
	@Transactional
	public int getCount(String userId) 
		{
		log.debug("ShoppingCart getCount function activated");
		List<ShoppingCart> list= getUserCart(userId);
		if(list.isEmpty())
			{
				log.debug("ShoppingCart getCount function returned zero");
				return 0;
			}
		else
			{
				log.debug("ShoppingCart getCount function returned listcount");
				return list.size();
			}
	}
}	//**********************************************Class End**************************************************************
