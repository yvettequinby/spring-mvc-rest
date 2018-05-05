package com.javafreelancedeveloper.springmvcrest.service;

import java.util.List;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> listCustomers();

	CustomerDTO getCustomerById(Long id);

	CustomerDTO saveCustomer(CustomerDTO customerDTO);

	CustomerDTO patchCustomer(CustomerDTO customerDTO);

	void deleteCustomer(Long id);
}
