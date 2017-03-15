package com.aruntech.shoppingcartbackend.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aruntech.shoppingcartbackend.dao.ProductDAO;
import com.aruntech.shoppingcartbackend.model.Product;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO,Serializable{
	@Autowired
	SessionFactory sessionFactory;
	
	//**********************************************Constructor************************************************************
	private static Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);
	public ProductDAOImpl(SessionFactory sessionFactory)
		{
			super();
			this.sessionFactory = sessionFactory;
		}
	
	//**********************************************Save data to database**************************************************
	
	@Transactional
	public String save(Product product) 
	{
		log.debug("Product Save function activated");
		try 
		{
			if (get(product.getId()) != null)
				{
					log.debug("Product Save function :ID error");
					return "idError";
				}
			else 
			{
				sessionFactory.getCurrentSession().save(product);
				log.debug("Product Save function successfull");
				return "success";
			} 
		} 
		catch (HibernateException e) 
			{
				e.printStackTrace();
				log.debug("Product Save function exception "+e.getMessage());
				return e.getMessage();
			}
		}
	
	

	//**********************************************Update data on DB******************************************************

	@Transactional
	public boolean update(Product product) 
	{
		log.debug("Product update function activated");
		try 
		{
			if (get(product.getId()) == null)
				{
					log.debug("Product update function ID not found");
					return false;
				}
			else 
			{
				product=(Product) sessionFactory.getCurrentSession().merge(product);
				sessionFactory.getCurrentSession().update(product);
				log.debug("Product update function successfull");
				return true;
			} 
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			log.debug("Product update function exception occured "+e.getMessage());
			return false;
		}
	}
	
	//**********************************************Delete data from DB****************************************************

	@Transactional
	public boolean delete(Product product) 
	{
		log.debug("Product delete function activated");
		try 
		{
			if(get(product.getId()) ==null)
			{
				log.debug("Product delete function :Id not found");
				return false;
			}
			product=(Product) sessionFactory.getCurrentSession().merge(product);
			sessionFactory.getCurrentSession().delete(product);
			log.debug("Product delete function successfull");
			return true;
		} 
		catch (HibernateException e) 
		{
			e.printStackTrace();
			log.debug("Product delete function exception occured "+e.getMessage());
			return false;
		}
	}

	//**********************************************Get single data from DB************************************************

	@Transactional
	public Product get(String ID) 
	{
		log.debug("Product get function activated");
		String hql ="from Product where ID='"+ID+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(query.list().isEmpty())
			{
				log.debug("Product get function :Id not found");
				return null;
			}
		else
			{
				log.debug("Product get function successfull");
				return (Product) query.list().get(0);
			}
		
	}

//**********************************************Get all data from DB***************************************************

	@Transactional
	public List<Product> getAll() 
		{
			log.debug("Product getAll function activated");
			String hql ="from Product order by id asc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Product get function successfull");
			return query.list();
		}
	
	
	
	@Transactional
	public List<Product> carouselList()
		{
			log.debug("CarouselList function activated");	
			Query query =sessionFactory.getCurrentSession().createSQLQuery("select count(*) from Product");
			BigInteger bigCount=(BigInteger)query.list().get(0);
			int count=bigCount.intValue();
			String hql="from Product order by addedon desc";
			query = sessionFactory.getCurrentSession().createQuery(hql);
			if(count<18)
			 	 query.setMaxResults(count);
			else
				 query.setMaxResults(18);
			log.debug("CarouselList function finished");	
			return query.list();
		}
	
	@Transactional
	public List<String> productNames() 
		{
			log.debug("Search Product function activated");	
			List<String> list=new ArrayList<String>();
			Query query=sessionFactory.getCurrentSession().createQuery("from Product");
			List<Product> prdList=query.list();
			for(int i=0;i<prdList.size();i++)
				list.add(prdList.get(i).getName());
			return list;
		}
		
}	//**********************************************Class End**************************************************************
