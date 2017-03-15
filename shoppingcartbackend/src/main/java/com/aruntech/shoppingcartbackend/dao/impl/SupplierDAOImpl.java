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

import com.aruntech.shoppingcartbackend.dao.SupplierDAO;
import com.aruntech.shoppingcartbackend.model.Supplier;

@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	private static Logger log = LoggerFactory.getLogger(SupplierDAOImpl.class);

//**********************************************Constructor************************************************************
	public SupplierDAOImpl(SessionFactory sessionFactory) 
		{
			this.sessionFactory = sessionFactory;
		}

	//**********************************************Save data to database**************************************************
	@Transactional
	public String save(Supplier supplier) 
		{
			log.debug("Supplier Save function activated");
			try 
				{
					if (get(supplier.getId()) != null) 
						{
							log.debug("Supplier Save function :ID error");
							return "idError";
						}
					sessionFactory.getCurrentSession().save(supplier);
					log.debug("supplier Save function successfull");
					return "success";
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("Supplier Save function exception "+e.getMessage());
					return e.getMessage();
				}
				
		}
	
	
	//**********************************************Update data on DB******************************************************
	
	@Transactional
	public boolean update(Supplier supplier) 
		{
			log.debug("Supplier update function activated");
			try 
				{
					if (get(supplier.getId()) == null)
						{
						log.debug("Supplier update function : ID error");
							return false;
						}
					supplier = (Supplier) sessionFactory.getCurrentSession().merge(supplier);
					sessionFactory.getCurrentSession().update(supplier);
					log.debug("Supplier update function successfull");
					return true;
				} 
			catch (HibernateException e)
				{
					e.printStackTrace();
					log.debug("Supplier update function exception "+e.getMessage());
					return false;
				}
		}
	
	
	//**********************************************Delete data from DB****************************************************

	@Transactional
	public boolean delete(Supplier supplier) 
		{
			log.debug("Supplier delete function activated");
			try 
				{
					if (get(supplier.getId()) == null) 
						{
						log.debug("Supplier delete function : ID error");
							return false;
						}
					supplier = (Supplier) sessionFactory.getCurrentSession().merge(supplier);
					sessionFactory.getCurrentSession().delete(supplier);
					log.debug("Supplier delete function successfull");
					return true;
				}
		catch (HibernateException e) 
			{
				e.printStackTrace();
				log.debug("Supplier delete function exception "+e.getMessage());
				return false;
			}
		}
	
	//**********************************************Get single data from DB************************************************

	
	@Transactional
	public Supplier get(String ID)
		{
			log.debug("Supplier get function activated");
			return sessionFactory.getCurrentSession().get(Supplier.class, ID);
		}
	
	//**********************************************Get all data from DB***************************************************
	
	@Transactional
	public List<Supplier> getAll() 
		{
			log.debug("Supplier geAll function activated");
			try 
				{
					String hql = "from Supplier order by id asc";
					Query query = sessionFactory.getCurrentSession().createQuery(hql);
					log.debug("Supplier getAll function successfull");
					return query.list();
				} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					log.debug("Supplier GetAll function exception "+e.getMessage());
					return null;
				}
		}

}//**********************************************Class End**************************************************************
