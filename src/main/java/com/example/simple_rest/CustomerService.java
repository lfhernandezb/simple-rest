package com.example.simple_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerCrudRepository customerCrudRepository;

    public Iterable<Customer> findAll() {
        return customerCrudRepository.findAll();
    }

    public Optional<Customer> findById(int id) {
        return customerCrudRepository.findById(id);
    }

    public Optional<Customer> findByFirstName(String firstName) {
        return customerCrudRepository.findByFirstName(firstName);
    }

    public Customer save(Customer customer) {
        return customerCrudRepository.save(customer);
    }

    public void deleteById(int id) {
        customerCrudRepository.deleteById(id);
    }
}
