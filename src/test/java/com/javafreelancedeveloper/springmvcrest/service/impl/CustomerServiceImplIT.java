package com.javafreelancedeveloper.springmvcrest.service.impl;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javafreelancedeveloper.springmvcrest.api.v1.mapper.CustomerMapper;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.bootstrap.Bootstrap;
import com.javafreelancedeveloper.springmvcrest.domain.Customer;
import com.javafreelancedeveloper.springmvcrest.repositories.CategoryRepository;
import com.javafreelancedeveloper.springmvcrest.repositories.CustomerRepository;
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	private CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		System.out.println("Loading Customer Data");
		System.out.println(customerRepository.findAll().size());
		// setup data for testing
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
		bootstrap.run(); // load data
		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@Test
	public void patchCustomerUpdateFirstName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(updatedName);
		customerDTO.setId(id);
		customerService.patchCustomer(customerDTO);
		Customer updatedCustomer = customerRepository.findById(id).get();
		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getFirstName());
		assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
		assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
	}

	@Test
	public void patchCustomerUpdateLastName() throws Exception {
		String updatedName = "UpdatedName";
		long id = getCustomerIdValue();
		Customer originalCustomer = customerRepository.getOne(id);
		assertNotNull(originalCustomer);
		String originalFirstName = originalCustomer.getFirstName();
		String originalLastName = originalCustomer.getLastName();
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(updatedName);
		customerDTO.setId(id);
		customerService.patchCustomer(customerDTO);
		Customer updatedCustomer = customerRepository.findById(id).get();
		assertNotNull(updatedCustomer);
		assertEquals(updatedName, updatedCustomer.getLastName());
		assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
		assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
	}

	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Customers Found: " + customers.size());
		return customers.get(0).getId();
	}
}
