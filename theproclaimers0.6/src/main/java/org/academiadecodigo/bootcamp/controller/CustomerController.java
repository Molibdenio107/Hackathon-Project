package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Customer;
import org.academiadecodigo.bootcamp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", customerId));
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
                               @Valid @RequestBody Customer noteDetails) {

        Customer note = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", customerId));

        note.setUsername(noteDetails.getUsername());
        note.setPassword(noteDetails.getPassword());

        Customer updatedNote = customerRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long customerId) {
        Customer note = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", customerId));

        customerRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
