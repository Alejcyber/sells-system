package com.alejcyber.SellSystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="`user`", 
uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"), 
    @UniqueConstraint(columnNames = "email") 
})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name="name")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    @Column(name="email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "`user_roles`", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
    
    public User() {}
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return "User [email=" + email + ", id=" + id + ", name=" + name + ", password=" + password + ", roles=" + roles
                + ", username=" + username + "]";
    }
}