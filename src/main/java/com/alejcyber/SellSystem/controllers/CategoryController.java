package com.alejcyber.SellSystem.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alejcyber.SellSystem.entities.Category;
import com.alejcyber.SellSystem.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    @GetMapping("/list-category")
    public String showCategoryList(Model model,  HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "categories/list-categories";
        }
        return "redirect:/admin-dashboard";
    }
    
    @GetMapping("/signup")
    public String showSignUpForm(Category category,  HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "categories/add-category";
        }
        return "redirect:/admin-dashboard";
    }
    
    @PostMapping("/addcategory")
    public String addCategory(@Valid Category category, BindingResult result, Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            if (result.hasErrors()) {
                return "categories/add-category";
            }
            categoryRepository.save(category);
            return "redirect:/category/list-category";
        }
        return "redirect:/admin-dashboard";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
            model.addAttribute("category", category);
            
            return "categories/update-category";
        }
        return "redirect:/admin-dashboard";
    }
    
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") long id, @Valid Category category, BindingResult result, Model model, HttpServletRequest request) {     
        if (request.isUserInRole("ROLE_ADMIN")) {
            if (result.hasErrors()) {
                category.setId(id);
                return "categories/update-category";
            }
            categoryRepository.save(category);
            return "redirect:/category/list-category";
        }
        return "redirect:/admin-dashboard";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
            categoryRepository.delete(category);
            return "redirect:/category/list-category";
        }
        return "redirect:/admin-dashboard";
    }
}