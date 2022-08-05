package org.academiadecodigo.bootcamp.controller.rest;

import org.academiadecodigo.bootcamp.command.CustomerDto;
import org.academiadecodigo.bootcamp.converters.CustomerConverter;
import org.academiadecodigo.bootcamp.persistence.model.Customer;
import org.academiadecodigo.bootcamp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for {@link Customer} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class RestCustomerController {

    private CustomerService customerService;
    private CustomerConverter customerConverter;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Sets the converter for converting between customer DTO and customer model objects
     *
     * @param customerConverter the customer DTO to customer converter to set
     */
    @Autowired
    public void setCustomerConverter(CustomerConverter customerConverter) {
        this.customerConverter = customerConverter;
    }

    /**
     * Retrieves a representation of the list of customers
     *
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<CustomerDto>> listCustomers() {

        List<CustomerDto> customerDtos = customerService.list().stream()
                .map(customer -> customerConverter.converttoDTO(customer))
                .collect(Collectors.toList());

        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    /**
     * Retrieves a representation of the given customer
     *
     * @param id the customer id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<CustomerDto> showCustomer(@PathVariable Integer id) {

        Customer customer = customerService.get(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customerConverter.converttoDTO(customer), HttpStatus.OK);
    }

    /**
     * Adds a customer
     *
     * @param customerDto          the customer DTO
     * @param bindingResult        the binding result object
     * @param uriComponentsBuilder the uri components builder
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || customerDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer savedCustomer = customerService.save(customerConverter.convert(customerDto));

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/api/customer/" + savedCustomer.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Edits a customer
     *
     * @param customerDto   the customer DTO
     * @param bindingResult the binding result
     * @param id            the customer id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<CustomerDto> editCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerDto.getId() != null && !customerDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (customerService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        customerDto.setId(id);

        customerService.save(customerConverter.convert(customerDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Deletes a customer
     *
     * @param id the customer id
     * @return the response entity
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable Integer id) {

        try {

            customerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }
}