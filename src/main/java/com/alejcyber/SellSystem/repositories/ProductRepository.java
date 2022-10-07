package com.alejcyber.SellSystem.repositories;

import java.util.List;

import com.alejcyber.SellSystem.entities.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllBySellerId(Long id);

    @Query("select p from Product p where p.seller.id <> ?1 ")
    List<Product> findProductsToBuy(Long id);
}
