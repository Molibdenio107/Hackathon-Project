package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Addresses;
import org.academiadecodigo.bootcamp.repository.AddressesRepository;
import org.academiadecodigo.bootcamp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    @Autowired
    AddressesRepository addresses;

    @Autowired
    CustomerRepository customer;

    @GetMapping("")
    public List<Addresses> getAllCustomers() {
        return addresses.findAll();
    }

    @PostMapping("")
    public Addresses createCustomer(@Valid @RequestBody Addresses address) {
        return this.addresses.save(address);
    }

    @GetMapping("/{id}")
    public Addresses getCustomerById(@PathVariable(value = "id") Long addressId) {
        return addresses.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", addressId));
    }

    @PutMapping("/{id}")
    public Addresses updateCustomer(@PathVariable(value = "id") Long addressId,
                                   @Valid @RequestBody Addresses addressDetails) {

        Addresses adressfound = addresses.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", addressId));

        /**
         * Review all get/set to ensure that all are here
         */
        adressfound.setId(addressId);
        adressfound.setCustomer(addressDetails.getCustomer());
        adressfound.setStreet(addressDetails.getStreet());
        adressfound.setNumber(addressDetails.getNumber());
        adressfound.setZipCode(addressDetails.getZipCode());
        adressfound.setCountry(addressDetails.getCountry());


        Addresses updatedAddress = addresses.save(adressfound);
        return updatedAddress;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long addressId) {
        Addresses customer1 = addresses.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", addressId));

        addresses.delete(customer1);

        return ResponseEntity.ok().build();
    }

}
