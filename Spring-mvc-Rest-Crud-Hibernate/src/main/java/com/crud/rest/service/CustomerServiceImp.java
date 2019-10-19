package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.MyCustomer;
import com.crud.rest.dao.CustomerDAO;

public class CustomerServiceImp implements CustomerService
{

	@Autowired
	private	CustomerDAO custdao;

    //setter for customer dao
	public void setCustdao(CustomerDAO custdao) {
		this.custdao = custdao;
	}

	public MyCustomer findById(long id) {
		
		return custdao.findById(id);
	}

	public MyCustomer findByName(String name) {
		
		return custdao.findByName(name);
	}

	public void saveCustomer(MyCustomer cust) {
		custdao.saveCustomer(cust);

	}

	public void updateCustomer(MyCustomer cust) {
		custdao.updateCustomer(cust);

	}

	public void deleteCustomer(long id) {
		custdao.deleteCustomer(id);

	}

	public List<MyCustomer> findAllCustomer() {
		
		return custdao.findAllCustomer();
	}

	public void deleteAllCustomers() {
		custdao.deleteAllCustomers();

	}

	public boolean isCustomerExist(MyCustomer cust) {
		
		return custdao.isCustomerExist(cust);
	}

}
