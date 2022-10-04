package com.alejcyber.SellSystem.repositories;

import java.util.List;

import com.alejcyber.SellSystem.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    List<User> findByName(String name);
    
}
