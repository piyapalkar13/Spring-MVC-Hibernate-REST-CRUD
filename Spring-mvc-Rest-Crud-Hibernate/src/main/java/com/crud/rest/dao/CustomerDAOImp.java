package com.crud.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.MyCustomer;

public class CustomerDAOImp implements CustomerDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	
	//setter for SessionFactory
	

	public MyCustomer findById(long id) {

		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		MyCustomer mycust=new MyCustomer();
		try
		{
			mycust=(MyCustomer)session.get(MyCustomer.class, id);
			tx.commit();
			session.close();
		}
		catch (Exception e) {
			tx.rollback();
			session.close();
		}
		return mycust;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public MyCustomer findByName(String name) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		MyCustomer mycust=new MyCustomer();
		String hql="from MyCustomer where name=?";
		try
		{

			Query query=session.createQuery(hql);
			query.setParameter(0, name);
			mycust=(MyCustomer)query.uniqueResult();
			tx.commit();
			session.close();

		}
		catch (Exception e) {
			tx.rollback();
			session.close();
		}
		return mycust;
	}

	public void saveCustomer(MyCustomer cust) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();

		if(cust!=null)
		{
			session.save(cust);
			tx.commit();
			session.close();
		}
		else
		{
			tx.rollback();
			session.close();
		}

	}

	public void updateCustomer(MyCustomer cust) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();

		if(cust!=null)
		{
			session.update(cust);
			tx.commit();
			session.close();
		}
		else
		{
			tx.rollback();
			session.close();
		}

	}

	public void deleteCustomer(long id) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try
		{
			MyCustomer mycust=(MyCustomer)	session.get(MyCustomer.class, id);
			session.delete(mycust);
			tx.commit();
			session.close();

		}
		catch (Exception e) {
			tx.rollback();
			session.close();
		}


	}

	public List<MyCustomer> findAllCustomer() {
		List<MyCustomer> custlist=new ArrayList<MyCustomer>();

		Session session=sessionFactory.openSession();

		String hql="from MyCustomer";

		Query query=session.createQuery(hql);
		query.list();

		return custlist;
	}

	public void deleteAllCustomers() {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();

		String hql="delete from MyCustomer";
		try
		{
			Query query=session.createQuery(hql);
			query.executeUpdate();

			tx.commit();
			session.close();
		}

		catch (Exception e) {

			tx.rollback();
			session.close();
		}

	}

	public boolean isCustomerExist(MyCustomer cust) {
		
		return findByName(cust.getName())!=null;
	}

}
