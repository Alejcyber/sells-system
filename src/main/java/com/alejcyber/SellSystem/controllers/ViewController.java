package com.alejcyber.SellSystem.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({ "/login","/index", "/" })
    public String login() {
        return "login";
    }

    @RequestMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

}