package com.alejcyber.SellSystem.repositories;

import java.util.List;
import java.util.Optional;

import com.alejcyber.SellSystem.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    List<User> findByName(String name);
    Optional<User> findByUsername(String username);
    
}
