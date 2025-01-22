package com.example.simple_rest;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerCrudRepository extends CrudRepository<Customer, Integer> {
    Optional<Customer> findByFirstName(String firstName);

}
