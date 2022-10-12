package com.alejcyber.SellSystem.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.alejcyber.SellSystem.config.UserDetailsImpl;
import com.alejcyber.SellSystem.controllers.forms.ProductForm;
import com.alejcyber.SellSystem.entities.Category;
import com.alejcyber.SellSystem.entities.Order;
import com.alejcyber.SellSystem.entities.OrderDetail;
import com.alejcyber.SellSystem.entities.Product;
import com.alejcyber.SellSystem.entities.User;
import com.alejcyber.SellSystem.repositories.CategoryRepository;
import com.alejcyber.SellSystem.repositories.OrderDetailRepository;
import com.alejcyber.SellSystem.repositories.OrderRepository;
import com.alejcyber.SellSystem.repositories.ProductRepository;
import com.alejcyber.SellSystem.repositories.UserRepository;
import com.alejcyber.SellSystem.utils.CartInfo;
import com.alejcyber.SellSystem.utils.CartLineInfo;
import com.alejcyber.SellSystem.utils.CartSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(
        ProductRepository productRepository, 
        UserRepository userRepository,
        OrderRepository orderRepository,
        OrderDetailRepository orderDetailRepository,
        CategoryRepository categoryRepository
        ) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.categoryRepository = categoryRepository;
    }
    
    @GetMapping("/seller/list-product")
    public String showProductListOfUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Long id = userDetails.getId();
        List<Product> products = productRepository.findAllBySellerId(id);
        model.addAttribute("products", products.size() != 0 ? products : null);
        return "products/seller/list-products";
    }
    
    @GetMapping("/seller/signup")
    public String showSignUpForm(ProductForm productForm, Model model) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories.size() != 0 ? categories : null);
        return "products/seller/add-product";
    }
    
    @PostMapping("/seller/addproduct")
    public String addProduct(@Valid ProductForm productForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Category> categories = (List<Category>) categoryRepository.findAll();
            model.addAttribute("categories", categories.size() != 0 ? categories : null);
            return "products/seller/add-product";
        }
        Product product = new Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        Category category = categoryRepository.findById(productForm.getCategoryId()).get();
        product.setCategory(category);
        User seller = getLoggedUser();
        product.setSeller(seller);
        productRepository.save(product);
        return "redirect:/product/seller/list-product";
    }
    
    
    @GetMapping("/seller/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model, ProductForm productForm) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories.size() != 0 ? categories : null);
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        //ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setName(product.getName());
        productForm.setPrice(product.getPrice());
        productForm.setCategoryId(product.getCategory().getId());
        model.addAttribute("productForm", productForm);

        return "products/seller/update-product";
    }
    
    @PostMapping("/seller/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid ProductForm productForm, BindingResult result, Model model) {
        Product product = new Product();
        product.setId(id);
        if (result.hasErrors()) {
            List<Category> categories = (List<Category>) categoryRepository.findAll();
            model.addAttribute("categories", categories.size() != 0 ? categories : null);
            return "products/seller/update-product";
        }
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        Category category = categoryRepository.findById(productForm.getCategoryId()).get();
        product.setCategory(category);
        Product oldProductData = productRepository.findById(id).get();
        product.setSeller(oldProductData.getSeller());
        
        productRepository.save(product);
        
        return "redirect:/product/seller/list-product";
    }
    
    @GetMapping("/seller/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productRepository.delete(product);
        
        return "redirect:/product/seller/list-product";
    }

    @GetMapping("/seller/my-sells")
    public String mySells(Model model) {
        User logged = getLoggedUser();
        List<OrderDetail> orderDetails = orderDetailRepository
        .findAllByProductSellerId(logged.getId());
        Double total = orderDetails.stream().mapToDouble(OrderDetail::getAmount).sum();
        model.addAttribute("orderDetails", orderDetails.size() != 0 ? orderDetails : null);
        model.addAttribute("total", total);
        return "products/seller/my-sells";
    }
    
    @GetMapping("/buyer/list-product")
    public String showProductList(Model model,HttpServletRequest request) {
        User logged = getLoggedUser();
        List<Product> products = productRepository.findProductsToBuy(logged.getId());
        model.addAttribute("products", products.size() != 0 ? products : null);
        CartInfo cartInfo = CartSession.getCartInSession(request);
        int quantity = cartInfo.getQuantityTotal();
        model.addAttribute("quantity", quantity);
        return "products/buyer/list-products";
    }

    @GetMapping("/buyer/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") long id, Model model,HttpServletRequest request) {
        Product productData = productRepository.findById(id).get();
        CartInfo cartInfo = CartSession.getCartInSession(request);
        cartInfo.addProduct(productData, 1);
        CartSession.updateCartInSession(request, cartInfo);
        
        return "redirect:/product/buyer/list-product";
    }

    @GetMapping("/buyer/checkout")
    public String checkout(Model model,HttpServletRequest request) {
        CartInfo cartInfo = CartSession.getCartInSession(request);
        List<CartLineInfo> cartLines = cartInfo.getCartLines();
        model.addAttribute("cartLines", cartLines.size() != 0 ? cartLines : null);
        int quantity = cartInfo.getQuantityTotal();
        model.addAttribute("quantity", quantity);
        Double total = cartInfo.getAmountTotal();
        model.addAttribute("total", total);
        return "products/buyer/checkout";
    }

    @GetMapping("/buyer/remove-to-cart/{id}")
    public String removeToCart(@PathVariable("id") long id, Model model,HttpServletRequest request) {
        Product productData = productRepository.findById(id).get();
        CartInfo cartInfo = CartSession.getCartInSession(request);
        cartInfo.removeProduct(productData);
        CartSession.updateCartInSession(request, cartInfo);
        return "redirect:/product/buyer/checkout";
    }

    @GetMapping("/buyer/done")
    public String done(HttpServletRequest request) {
        CartInfo cartInfo = CartSession.getCartInSession(request);
        Double total = cartInfo.getAmountTotal();
        User buyer = getLoggedUser();
        Order order = new Order();
        order.setBuyer(buyer);
        order.setDate(new Date());
        order.setTotal(total);
        Order savedOrder = orderRepository.save(order);
        List<CartLineInfo> cartLines = cartInfo.getCartLines();
        for(CartLineInfo cartLineInfo: cartLines){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(cartLineInfo.getProduct());
            orderDetail.setOrder(savedOrder);
            orderDetail.setQuantity(cartLineInfo.getQuantity());
            orderDetail.setAmount(cartLineInfo.getAmount());
            orderDetailRepository.save(orderDetail);
        }

        CartSession.removeCartInSession(request);
        return "redirect:/product/buyer/my-shopping";
    }

    @GetMapping("/buyer/my-shopping")
    public String myShopping(Model model) {
        User logged = getLoggedUser();
        List<OrderDetail> orderDetails = orderDetailRepository
        .findAllByOrderBuyerId(logged.getId());
        model.addAttribute("orderDetails", orderDetails.size() != 0 ? orderDetails : null);
        return "products/buyer/my-shopping";
    }

    @GetMapping("/buyer/view-order/{id}")
    public String viewOrder(@PathVariable("id") long id, Model model) {
        Order order = orderRepository.findById(id).get();
        List<OrderDetail> orderDetails = orderDetailRepository
        .findAllByOrderId(order.getId());
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails.size() != 0 ? orderDetails : null);
        return "products/buyer/view-order";
    }

    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Long id = userDetails.getId();
        User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + id));
        return user;
    }
}