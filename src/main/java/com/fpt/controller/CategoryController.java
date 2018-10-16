package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpt.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
     @Autowired 
     private CategoryService categoryServiceImpl;
     
     @RequestMapping(path = "", method = RequestMethod.GET)
     public ResponseEntity<?> getCategory(){
    	 return new ResponseEntity<>(categoryServiceImpl.findAll(),HttpStatus.OK);
     }
     
     
}
