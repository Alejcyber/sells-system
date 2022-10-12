package com.alejcyber.SellSystem.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.alejcyber.SellSystem.entities.Category;
import com.alejcyber.SellSystem.entities.ERole;
import com.alejcyber.SellSystem.entities.Product;
import com.alejcyber.SellSystem.entities.Role;
import com.alejcyber.SellSystem.entities.User;
import com.alejcyber.SellSystem.repositories.CategoryRepository;
import com.alejcyber.SellSystem.repositories.ProductRepository;
import com.alejcyber.SellSystem.repositories.RoleRepository;
import com.alejcyber.SellSystem.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class InitializeDatabaseBean {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
     
        List<Role> rolesSaved = roleRepository.findAll();
        if(rolesSaved.size() == 0){

            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            Role roleUser = new Role(ERole.ROLE_USER);
            Role roleMod = new Role(ERole.ROLE_MODERATOR);
            Role roleAdminSaved = roleRepository.save(roleAdmin);
            Role roleUserSaved = roleRepository.save(roleUser);
            Role roleModSaved = roleRepository.save(roleMod);

            Set<Role> rolesAdmin = new HashSet<>();
            rolesAdmin.add(roleAdminSaved);

            Set<Role> rolesUser = new HashSet<>();
            rolesUser.add(roleUserSaved);

            User user = new User();
            user.setName("User 1");
            user.setUsername("user1");
            user.setEmail("a@gmail.com");
            user.setPassword(passwordEncoder.encode("1"));
            user.setRoles(rolesUser);
            userRepository.save(user);

            User user2 = new User();
            user2.setName("User 2");
            user2.setUsername("user2");
            user2.setEmail("b@gmail.com");
            user2.setPassword(passwordEncoder.encode("1"));
            user2.setRoles(rolesUser);
            userRepository.save(user2);

            User user3 = new User();
            user3.setName("User 3");
            user3.setUsername("user3");
            user3.setEmail("c@gmail.com");
            user3.setPassword(passwordEncoder.encode("1"));
            user3.setRoles(rolesAdmin);
            userRepository.save(user3);

            Category fruits = new Category();
            fruits.setName("Fruits");
            categoryRepository.save(fruits);

            Category cereals = new Category();
            cereals.setName("Cereals");
            categoryRepository.save(cereals);

            Category meats = new Category();
            meats.setName("Meats");
            categoryRepository.save(meats);

            Product p1 = new Product();
            p1.setName("Apple");
            p1.setPrice(12.00);
            p1.setSeller(user);
            p1.setCategory(fruits);
            productRepository.save(p1);
            
            Product p2 = new Product();
            p2.setName("Banana");
            p2.setPrice(5.00);
            p2.setSeller(user);
            p2.setCategory(fruits);
            productRepository.save(p2);

            Product p3 = new Product();
            p3.setName("Mango");
            p3.setPrice(5.00);
            p3.setSeller(user);
            p3.setCategory(fruits);
            productRepository.save(p3);

            Product p4 = new Product();
            p4.setName("Rice");
            p4.setPrice(12.00);
            p4.setSeller(user2);
            p4.setCategory(cereals);
            productRepository.save(p4);
            
            Product p5 = new Product();
            p5.setName("Quinoa");
            p5.setPrice(5.00);
            p5.setSeller(user2);
            p5.setCategory(cereals);
            productRepository.save(p5);

            Product p6 = new Product();
            p6.setName("Corn");
            p6.setPrice(5.00);
            p6.setSeller(user2);
            p6.setCategory(cereals);
            productRepository.save(p6);

            Product p7 = new Product();
            p7.setName("Pork");
            p7.setPrice(12.00);
            p7.setSeller(user3);
            p7.setCategory(cereals);
            productRepository.save(p7);
            
            Product p8 = new Product();
            p8.setName("Beef");
            p8.setPrice(5.00);
            p8.setSeller(user3);
            p8.setCategory(cereals);
            productRepository.save(p8);

            Product p9 = new Product();
            p9.setName("Chicken");
            p9.setPrice(5.00);
            p9.setSeller(user3);
            p9.setCategory(cereals);
            productRepository.save(p9);
        }

    }
}
