package com.crud.rest.dao;

import java.util.List;

import com.crud.rest.beans.MyCustomer;

public interface CustomerDAO {

	MyCustomer findById(long id);
	MyCustomer findByName(String name);
    void saveCustomer(MyCustomer cust);
    void updateCustomer(MyCustomer cust);
    void deleteCustomer(long id);
    List<MyCustomer> findAllCustomer();
    void deleteAllCustomers();
    boolean isCustomerExist(MyCustomer cust);
	
	
	
}
