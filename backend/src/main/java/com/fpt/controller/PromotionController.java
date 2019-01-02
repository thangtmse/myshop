package com.fpt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fpt.dto.request.PromotionRequest;
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
       
       @RequestMapping(path = "{id}", method = RequestMethod.GET)
       public ResponseEntity<?> getonePromotion(@PathVariable("id") Long id){
    	   return new ResponseEntity<>(promotionMapper.toPromotionResponse(promotionService.getOnePromotion(id)),HttpStatus.OK);
       }
       @RequestMapping(path = "/accept", method = RequestMethod.POST)
       @ResponseStatus(value = HttpStatus.CREATED)
   	public void create(@RequestBody PromotionRequest pr) {
    	   List<Promotion> lp=promotionMapper.toPromotion(pr);
   		promotionService.create(lp);
   				
   				
   	}
      
       
       @RequestMapping(path = "/all", method = RequestMethod.GET)
       public ResponseEntity<?> getAllPromotion(String name,Pageable pageable) throws Exception{
    	   return new ResponseEntity<>(promotionMapper.toPromotionResponse(promotionService.findAll(name, pageable)),HttpStatus.OK);
       }
       @RequestMapping(path = "{id}", method = RequestMethod.PUT)
   	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Promotion promotion) {
   		promotion.setPromotionId(id);
   		return new ResponseEntity<>(promotionService.update(promotion), HttpStatus.OK);
   	}
//       @RequestMapping(path = "", method = RequestMethod.GET)
//       public ResponseEntity<?> getPromotion(@RequestParam String code){
//    	   return new ResponseEntity<>(promotionService.getpromotion(code),HttpStatus.OK);
//       }
       @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
       @ResponseStatus(value = HttpStatus.OK)
   	public void delete(@PathVariable("id") Long id) {
   		promotionService.delete(id);
   	}
       
}
