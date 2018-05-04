package com.javafreelancedeveloper.springmvcrest.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javafreelancedeveloper.springmvcrest.domain.Category;
import com.javafreelancedeveloper.springmvcrest.domain.Customer;
import com.javafreelancedeveloper.springmvcrest.repositories.CategoryRepository;
import com.javafreelancedeveloper.springmvcrest.repositories.CustomerRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Category fruits = new Category();
		fruits.setName("Fruits");
		Category dried = new Category();
		dried.setName("Dried");
		Category fresh = new Category();
		fresh.setName("Fresh");
		Category exotic = new Category();
		exotic.setName("Exotic");
		Category nuts = new Category();
		nuts.setName("Nuts");
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		System.out.println("Category Data Loaded = " + categoryRepository.count());
		
		Customer customer1 = new Customer();
		customer1.setFirstName("Joe");
		customer1.setLastName("Macmillan");
		Customer customer2 = new Customer();
		customer2.setFirstName("Cameron");
		customer2.setLastName("Howe");
		customerRepository.save(customer1);
		customerRepository.save(customer2);
		System.out.println("Customer Data Loaded = " + customerRepository.count());
	}
}
