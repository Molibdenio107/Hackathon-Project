package org.academiadecodigo.bootcamp.services;

import org.academiadecodigo.bootcamp.persistence.model.Customer;

import java.util.List;

/**
 * Common interface for customer services, provides methods to manage customers
 */
public interface CustomerService {

    /**
     * Gets the customer with the given id
     *
     * @param id the customer id
     * @return the customer
     */
    Customer get(Integer id);


    /**
     *
     */
    Customer save(Customer customer);

    /**
     *
     */
    void delete(Integer id);

    /**
     * Gets a list of the customers
     *
     * @return the customers list
     */
    List<Customer> list();


}
