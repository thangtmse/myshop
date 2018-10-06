package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.entity.Product;
import com.fpt.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productServiceImpl;
    
    @RequestMapping("/home")
    public String index(Model model) {
    	model.addAttribute("Product", productServiceImpl.findAll());
    	return "home";
    }
    @RequestMapping("/producthome")
    public ResponseEntity<?> getProduct(){
    	
    	return new ResponseEntity<>(productServiceImpl.findAll(),HttpStatus.OK);
    }
    
}
