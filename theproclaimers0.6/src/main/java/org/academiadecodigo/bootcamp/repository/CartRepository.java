package org.academiadecodigo.bootcamp.repository;

import org.academiadecodigo.bootcamp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
