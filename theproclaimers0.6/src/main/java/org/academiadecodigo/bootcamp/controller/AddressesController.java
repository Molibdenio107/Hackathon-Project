package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.model.Addresses;
import org.academiadecodigo.bootcamp.model.Customer;
import org.academiadecodigo.bootcamp.repository.AddressesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    @Autowired
    private AddressesRepository addresses;

    @GetMapping("")
    public List<Addresses> getAllCustomers() {
        return addresses.findAll();
    }

    @PostMapping("")
    public Addresses createCustomer(@Valid @RequestBody Addresses Addresses) {
        return addresses.save(Addresses);
    }
}
