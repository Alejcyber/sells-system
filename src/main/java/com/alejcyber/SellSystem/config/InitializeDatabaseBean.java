package com.alejcyber.SellSystem.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.alejcyber.SellSystem.entities.ERole;
import com.alejcyber.SellSystem.entities.Product;
import com.alejcyber.SellSystem.entities.Role;
import com.alejcyber.SellSystem.entities.User;
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
            roleRepository.save(roleUser);
            roleRepository.save(roleMod);

            User user = new User();
            user.setName("User 1");
            user.setUsername("user1");
            user.setEmail("a@gmail.com");
            user.setPassword(passwordEncoder.encode("1"));
            Set<Role> roles = new HashSet<>();
            roles.add(roleAdminSaved);
            user.setRoles(roles);
            userRepository.save(user);

            User user2 = new User();
            user2.setName("User 2");
            user2.setUsername("user2");
            user2.setEmail("b@gmail.com");
            user2.setPassword(passwordEncoder.encode("1"));
            user2.setRoles(roles);
            userRepository.save(user2);

            Product p1 = new Product();
            p1.setName("Apple");
            p1.setPrice(12.00);
            p1.setSeller(user);
            productRepository.save(p1);
            
            Product p2 = new Product();
            p2.setName("Banana");
            p2.setPrice(5.00);
            p2.setSeller(user);
            productRepository.save(p2);

            Product p3 = new Product();
            p3.setName("Mango");
            p3.setPrice(5.00);
            p3.setSeller(user);
            productRepository.save(p3);

            Product p4 = new Product();
            p4.setName("Pinneaple");
            p4.setPrice(12.00);
            p4.setSeller(user2);
            productRepository.save(p4);
            
            Product p5 = new Product();
            p5.setName("Orange");
            p5.setPrice(5.00);
            p5.setSeller(user2);
            productRepository.save(p5);

            Product p6 = new Product();
            p6.setName("Watermelon");
            p6.setPrice(5.00);
            p6.setSeller(user2);
            productRepository.save(p6);
        }

    }
}
