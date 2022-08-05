package org.academiadecodigo.bootcamp.repository;

import org.academiadecodigo.bootcamp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
