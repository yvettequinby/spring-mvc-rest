package com.javafreelancedeveloper.springmvcrest.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerListDTO;
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

@Api(description = "Customer Controller API")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

	public static final String BASE_URL = "/api/v1/customers";
	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@ApiOperation(value = "List all customers.")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO listCustomers() {
		List<CustomerDTO> customers = customerService.listCustomers();
		CustomerListDTO customersDto = new CustomerListDTO(customers);
		return customersDto;
	}

	@ApiOperation(value = "Get a customer by ID.")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerById(@PathVariable Long id) {
		CustomerDTO customer = customerService.getCustomerById(id);
		return customer;
	}

	@ApiOperation(value = "Create a customer.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomer(customerDTO);
	}

	@ApiOperation(value = "Update a customer.")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomer(customerDTO);
	}

	@ApiOperation(value = "Patch a customer.", notes="Only fields with provided values will be updated in the customer record.")
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.patchCustomer(customerDTO);
	}
	
	@ApiOperation(value = "Delete a customer, by ID.")
	@DeleteMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
}
