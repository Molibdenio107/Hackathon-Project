package org.academiadecodigo.bootcamp.controller;

import org.academiadecodigo.bootcamp.command.CustomerDto;
import org.academiadecodigo.bootcamp.converter.CustomerConverter;
import org.academiadecodigo.bootcamp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    private CustomerConverter converter;
    private CustomerDto customerDto;

    /**
     *
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     *
     */
    @Autowired
    public void setCustomerDto(CustomerConverter converter) {
        this.converter = converter;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<CustomerDto>> listCustomers() {

        List<CustomerDto> customerDtos = customerService.list().stream()
                .map(customer -> converter.convertToCustomerDto(customer))
                .collect(Collectors.toList());

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }


}
