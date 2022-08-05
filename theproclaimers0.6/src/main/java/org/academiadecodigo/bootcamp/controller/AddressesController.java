package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.exception.ResourceNotFoundException;
import org.academiadecodigo.bootcamp.model.Addresses;
import org.academiadecodigo.bootcamp.repository.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    @Autowired
    AddressesRepository addresses;

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
                                   @Valid @RequestBody Addresses AddressDetails) {

        Addresses adressfound = addresses.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", addressId));

        /**
         * Review all get/set to ensure that all are here
         */
        adressfound.setId(addressId);
        adressfound.setCustomer(AddressDetails.getCustomer());
        adressfound.setStreet(AddressDetails.getStreet());
        adressfound.setNumber(AddressDetails.getNumber());
        adressfound.setZipCode(AddressDetails.getZipCode());
        adressfound.setCountry(AddressDetails.getCountry());

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
