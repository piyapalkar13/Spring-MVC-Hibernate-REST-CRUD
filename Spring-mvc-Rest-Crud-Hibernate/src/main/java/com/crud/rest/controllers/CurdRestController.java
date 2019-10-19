package com.crud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.rest.beans.MyCustomer;
import com.crud.rest.service.CustomerServiceImp;

@RestController //combination of @Controller and @ResponseBody annotation
public class CurdRestController {

	@Autowired
	private CustomerServiceImp customerService;

	//setter for  customerService
	public void setCustomerService(CustomerServiceImp customerService) {
		this.customerService = customerService;
	}
//add customer
	
	@RequestMapping(value="/customer/new",method=RequestMethod.POST)
	public ResponseEntity<Void> addCustomer(@RequestBody MyCustomer customer,UriComponentsBuilder ucb)
	{
		if(customerService.isCustomerExist(customer))
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		else
		{
			customerService.saveCustomer(customer);
			HttpHeaders headers=new HttpHeaders();
			headers.setLocation(ucb.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
		    return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	
	//Get Single customer
	@RequestMapping(value="/cust/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public	ResponseEntity<MyCustomer> getCustomer(@PathVariable("id") int id)
	{
		MyCustomer cust=(MyCustomer)customerService.findById(id);

		if(cust==null)
		{
			return new ResponseEntity<MyCustomer>(HttpStatus.NOT_FOUND);  
		}
		else
		{
			return new ResponseEntity<MyCustomer>(cust,HttpStatus.OK);
		}
	}

	//Get All Customers

	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ResponseEntity<List<MyCustomer>>  listAllCustomers()
	{
		List<MyCustomer>cust=customerService.findAllCustomer();

		if(cust.isEmpty())
		{
			return new ResponseEntity<List<MyCustomer>>(HttpStatus.NO_CONTENT);	
		}
		else
		{
			return new ResponseEntity<List<MyCustomer>>(cust,HttpStatus.OK);
		}
	}
	//Update customer
	@RequestMapping(value="/customer/{id}",method=RequestMethod.PUT)	
	public ResponseEntity<MyCustomer> updateCustomer(@PathVariable("id") long id,@RequestBody MyCustomer cust)
	{
		MyCustomer customer=(MyCustomer)customerService.findById(id);

		if(customer==null)
		{
			return new ResponseEntity<MyCustomer>(HttpStatus.NOT_FOUND);
		}
		else
		{
			customer.setName(cust.getName());
			customer.setAge(cust.getAge());
			customer.setEmail(cust.getEmail());
			customer.setCity(cust.getCity());

			customerService.updateCustomer(customer);
			return new ResponseEntity<MyCustomer>(cust,HttpStatus.OK);
		}
	}

	//delete customer
	@RequestMapping(value="/customer/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<MyCustomer> deleteCustomer(@PathVariable("id") long id)
	{
		MyCustomer cust=(MyCustomer)customerService.findById(id);
		if(cust==null)
		{
			return new ResponseEntity<MyCustomer>(HttpStatus.NOT_FOUND);
		}
		else
		{
			customerService.deleteCustomer(id);
			return new ResponseEntity<MyCustomer>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	//delete all customers
	@RequestMapping(value="/customers/deleteall",method=RequestMethod.DELETE)
	public ResponseEntity<MyCustomer> deleteAllCustomers()
	{
		customerService.deleteAllCustomers();
		return new ResponseEntity<MyCustomer>(HttpStatus.NO_CONTENT);
	}

}
