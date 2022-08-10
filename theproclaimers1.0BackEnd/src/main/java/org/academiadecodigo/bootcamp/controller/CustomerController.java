package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Customer;
import org.academiadecodigo.bootcamp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customer;

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customer.findAll();
    }

    @PostMapping("")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return this.customer.save(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) {
        Customer customer1 = customer.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", customerId));

        return new ResponseEntity<>(customer1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
                               @Valid @RequestBody Customer CustomerDetails) {

        Customer customerfound = customer.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", customerId));


        /**
         * Review all get/set to ensure that all are here
         */
        customerfound.setId(customerId);
        customerfound.setUsername(CustomerDetails.getUsername());
        customerfound.setPassword(CustomerDetails.getPassword());
        customerfound.setName(CustomerDetails.getName());
        customerfound.setEmail(CustomerDetails.getEmail());
        customerfound.setPhone(CustomerDetails.getPhone());
        customerfound.setNif(CustomerDetails.getNif());

        Customer updatedCustomer = customer.save(customerfound);
        return updatedCustomer;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        Customer customer1 = customer.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", customerId));

        customer.delete(customer1);

        return ResponseEntity.ok().build();
    }
}
