package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.service.CategoryService;

@Controller
public class CategoryController {
     @Autowired 
     private CategoryService categoryServiceImpl;
     
     @RequestMapping("category")
     public ResponseEntity<?> getCategory(){
    	 return new ResponseEntity<>(categoryServiceImpl.findAll(),HttpStatus.OK);
     }
}
