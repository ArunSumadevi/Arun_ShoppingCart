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

import com.aruntech.shoppingcartbackend.dao.CategoryDAO;
import com.aruntech.shoppingcartbackend.model.Category;
@SuppressWarnings("deprecation")
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO
{
	
	private static Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	//**********************************************Constructor************************************************************
	
	public CategoryDAOImpl(SessionFactory sessionFactory)
		{
			this.sessionFactory = sessionFactory;
		}

	//**********************************************Save data to database**************************************************
	
	@Transactional
	public String save(Category category) 
		{
			log.debug("Category Save function activated");
			try 
				{
					if (get(category.getId()) != null)
						{
							log.debug("Category Save function : Id present in DB");
							return "idError";
						}
					else 
						{
							sessionFactory.getCurrentSession().save(category);
							log.debug("Category Save function : success");
							return "Success";
						} 
				}
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("Category Save function : Exception :" + e.getMessage());
					return e.getMessage();
				}
		}

	//**********************************************Update data on DB******************************************************
	
	@Transactional
	public boolean update(Category category) 
		{
			log.debug("Category Update function activated");
			try 
				{
					if (get(category.getId()) == null)
						{
							log.debug("Category Update function : Id not found");
							return false;
						}
					else 
						{
							category=(Category) sessionFactory.getCurrentSession().merge(category);
							sessionFactory.getCurrentSession().update(category);
							log.debug("Category Update function: success");
							return true;
						} 
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("Category Update function: Exception :"+ e.getMessage());
					return false;
				}
		}
	
	//**********************************************Delete data from DB****************************************************

	@Transactional
	public boolean delete(Category category) 
		{
			log.debug("Category delete function activated");
			try 
				{
					if(get(category.getId()) ==null)
						{
							log.debug("Category delete function : Id not found");
							return false;
						}
					category=(Category) sessionFactory.getCurrentSession().merge(category);
					sessionFactory.getCurrentSession().delete(category);
					log.debug("Category delete function :success");
					return true;
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("Category delete function : Exception "+e.getMessage());
					return false;
				}
		}

	
	//**********************************************Get single data from DB************************************************
	
	@Transactional
	public Category get(String ID) 
		{
			log.debug("Category get function activated");
			return (Category) sessionFactory.getCurrentSession().get(Category.class, ID);
		}

	//**********************************************Get all data from DB***************************************************
	
	@Transactional
	public List<Category> getAll() 
		{
			log.debug("Category getall function activated");
			String hql ="from Category order by id ASc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Category getall function : success");
			return query.list();
		}

}   //**********************************************Class End**************************************************************
