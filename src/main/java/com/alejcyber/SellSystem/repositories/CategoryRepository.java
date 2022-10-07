package com.alejcyber.SellSystem.repositories;

import com.alejcyber.SellSystem.entities.Category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
