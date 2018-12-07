package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpt.entity.Promotion;
import com.fpt.mapper.PromotionMapper;
import com.fpt.service.PromotionService;

@Controller
@RequestMapping("/api/promotion")
public class PromotionController {
       @Autowired
       private PromotionService promotionService;
       @Autowired
       private PromotionMapper promotionMapper;
       
       @RequestMapping(path = "", method = RequestMethod.POST)
   	public ResponseEntity<?> create(@RequestBody Promotion promotion) {
   		return new ResponseEntity<>(
   				promotionMapper.toPromotionResponse(promotionService.create(promotion)),
   				HttpStatus.CREATED);
   	}
}
