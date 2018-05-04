package com.javafreelancedeveloper.springmvcrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javafreelancedeveloper.springmvcrest.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
