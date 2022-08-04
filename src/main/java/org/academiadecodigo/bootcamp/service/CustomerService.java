package org.academiadecodigo.bootcamp.service;

import org.academiadecodigo.bootcamp.persistence.model.Customer;
import org.academiadecodigo.bootcamp.persistence.model.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDao customerDao;

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     *
     */
    public Customer get(Integer id) {
        return customerDao.findById(id);
    }

    public List<Customer> list() {
        return customerDao.findAll();
    }
}

