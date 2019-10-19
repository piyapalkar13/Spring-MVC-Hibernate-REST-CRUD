package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.MyCustomer;


public interface CustomerService {

	MyCustomer findById(long id);
	MyCustomer findByName(String name);
    void saveCustomer(MyCustomer cust);
    void updateCustomer(MyCustomer cust);
    void deleteCustomer(long id);
    List<MyCustomer> findAllCustomer();
    void deleteAllCustomers();
    boolean isCustomerExist(MyCustomer cust);
	
}
