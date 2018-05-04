package com.javafreelancedeveloper.springmvcrest.controller.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerDTO;
import com.javafreelancedeveloper.springmvcrest.api.v1.model.CustomerListDTO;
import com.javafreelancedeveloper.springmvcrest.service.CustomerService;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public ResponseEntity<CustomerListDTO> listCustomers() {
		List<CustomerDTO> customers = customerService.listCustomers();
		CustomerListDTO customersDto = new CustomerListDTO(customers);
		return new ResponseEntity<CustomerListDTO>(customersDto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		CustomerDTO customer = customerService.getCustomerById(id);
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<CustomerDTO>(customerService.saveCustomer(customerDTO), HttpStatus.CREATED);
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<CustomerDTO>(customerService.saveCustomer(customerDTO), HttpStatus.OK);
	}
}
