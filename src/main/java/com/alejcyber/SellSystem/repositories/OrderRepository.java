package com.alejcyber.SellSystem.repositories;

import com.alejcyber.SellSystem.entities.Order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
