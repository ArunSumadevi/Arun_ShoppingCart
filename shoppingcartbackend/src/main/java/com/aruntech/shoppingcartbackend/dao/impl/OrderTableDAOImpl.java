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

import com.aruntech.shoppingcartbackend.dao.OrderTableDAO;
import com.aruntech.shoppingcartbackend.model.OrderTable;
import com.aruntech.shoppingcartbackend.model.Product;
import com.aruntech.shoppingcartbackend.model.Supplier;

@Repository("orderTableDAO")
public class OrderTableDAOImpl  implements OrderTableDAO, Serializable
{
	@Autowired
	SessionFactory sessionFactory;
	private static Logger log = LoggerFactory.getLogger(OrderTableDAOImpl.class);
	
	public OrderTableDAOImpl(SessionFactory sessionFactory) 
		{
			super();
			this.sessionFactory = sessionFactory;
		}

	@Transactional
	public boolean save(OrderTable orderTable) 
		{
			try 
			{
				if (get(orderTable.getId()) != null)
					return false;
				else 
				{
					sessionFactory.getCurrentSession().save(orderTable);
					return true;
				} 
			} 
			catch (HibernateException e) 
				{
					e.printStackTrace();
					return false;
				}
		}
	
	//**********************************************Update data on DB******************************************************
	
		@Transactional
		public boolean update(OrderTable orderTable) 
			{
				log.debug("OrderTable update function activated");
				try 
					{
						if (get(orderTable.getId()) == null)
							{
							log.debug("OrderTable update function : ID error");
								return false;
							}
						orderTable = (OrderTable) sessionFactory.getCurrentSession().merge(orderTable);
						sessionFactory.getCurrentSession().update(orderTable);
						log.debug("OrderTable update function successfull");
						return true;
					} 
				catch (HibernateException e)
					{
						e.printStackTrace();
						log.debug("OrderTable update function exception "+e.getMessage());
						return false;
					}
			}
		

		
	@Transactional
	public OrderTable get(String ID) 
	{
		log.debug("OrderTable get function activated");
		return sessionFactory.getCurrentSession().get(OrderTable.class, ID);
	}

	
	
	@Transactional
	public List<OrderTable> getUserOrder(String userId) 
		{
		String hql ="from OrderTable where userId='"+userId+"'order by number asc";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		}

	@Transactional
	public List<OrderTable> getByOrderNumber(String orderNumber)
		{
				String hql ="from OrderTable where number='"+orderNumber+"'";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				return query.list();
		}

	@Transactional
	public List<OrderTable> getOrderStatus(String orderNumber) {
		return sessionFactory.getCurrentSession().createQuery("from OrderTable where number='"+orderNumber +"' and productstatus <>'Received by user'").list();
	}
}
