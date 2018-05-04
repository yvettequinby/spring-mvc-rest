package com.javafreelancedeveloper.springmvcrest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafreelancedeveloper.springmvcrest.api.v1.mapper.CustomerMapper;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.domain.Customer;
import com.javafreelancedeveloper.springmvcrest.repositories.CustomerRepository;
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public List<CustomerDTO> listCustomers() {
		return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(RuntimeException::new);
	}

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
		returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());
		return returnDto;
	}

	
}
