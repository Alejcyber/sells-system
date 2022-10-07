package com.alejcyber.SellSystem.repositories;

import java.util.List;
import java.util.Optional;

import com.alejcyber.SellSystem.entities.ERole;
import com.alejcyber.SellSystem.entities.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    List<Role> findAll();
}
