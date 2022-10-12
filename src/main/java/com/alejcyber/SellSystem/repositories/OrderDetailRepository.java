package com.alejcyber.SellSystem.repositories;

import java.util.List;

import com.alejcyber.SellSystem.entities.OrderDetail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
    public List<OrderDetail> findAllByOrderBuyerId(Long id);
    public List<OrderDetail> findAllByProductSellerId(Long id);
    public List<OrderDetail> findAllByOrderId(Long id);
}
