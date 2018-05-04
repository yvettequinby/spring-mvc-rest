package com.javafreelancedeveloper.springmvcrest.api.v1.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.domain.Customer;

public class CustomerMapperTest {

	public static final String FIRST_NAME = "Joe";
	public static final String LAST_NAME = "Macmillan";
	public static final long ID = 1L;
	private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	@Test
	public void testCustomerToCustomerDTO() throws Exception {
		// given
		Customer customer = new Customer();
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		customer.setId(ID);
		// when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
		// then
		assertEquals(Long.valueOf(ID), customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
	}
}
