package com.javafreelancedeveloper.springmvcrest.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.javafreelancedeveloper.springmvcrest.api.v1.mapper.CustomerMapper;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.domain.Customer;
import com.javafreelancedeveloper.springmvcrest.repositories.CustomerRepository;
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

public class CustomerServiceImplTest {

	public static final Long ID = 2L;
	public static final String FIRST_NAME = "Jimmy";
	public static final String LAST_NAME = "McGill";
	private CustomerService customerService;
	@Mock
	private CustomerRepository customerRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@Test
	public void testListCustomers() throws Exception {
		// given
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
		when(customerRepository.findAll()).thenReturn(customers);
		// when
		List<CustomerDTO> customerDTOS = customerService.listCustomers();
		// then
		assertEquals(3, customerDTOS.size());
	}

	@Test
	public void testGetCustomerById() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		when(customerRepository.findById(ID)).thenReturn(Optional.of(customer));
		// when
		CustomerDTO customerDTO = customerService.getCustomerById(ID);
		// then
		assertEquals(ID, customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
	}

	@Test
	public void testSaveCustomer() throws Exception {
		// given
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Gordon");
		customerDTO.setLastName("Clark");
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1l);
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		// when
		CustomerDTO savedDto = customerService.saveCustomer(customerDTO);
		// then
		assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
		assertEquals(customerDTO.getLastName(), savedDto.getLastName());
		assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
	}
}
