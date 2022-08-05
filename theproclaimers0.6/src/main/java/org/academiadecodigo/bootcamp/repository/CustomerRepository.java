package org.academiadecodigo.bootcamp.repository;

import org.academiadecodigo.bootcamp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
