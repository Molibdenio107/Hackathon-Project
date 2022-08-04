package org.academiadecodigo.bootcamp.converter;

import org.academiadecodigo.bootcamp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractConverter {
    protected CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
