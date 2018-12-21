package com.fpt.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fpt.dto.request.PromotionRequest;
import com.fpt.dto.response.PromotionResponse;
import com.fpt.entity.Promotion;
import com.fpt.service.ProductService;
@Component
public class PromotionMapper {
      @Autowired
      private ProductService productService;
      
      public List<Promotion> toPromotion(PromotionRequest pr) {
    	  if(pr==null) {
    		  return null;
    	  }
    	  
    	  List<Promotion> p=new ArrayList<>();
    	  
    	  for (Long productId : pr.getProductIds()) {
    		  Promotion pro=new Promotion();
    	  pro.setCreateDate(pr.getCreateDate());
    	  pro.setExprieDate(pr.getExprieDate());
    	  pro.setDiscount(pr.getDiscount());
    	  pro.setDisountCode(pr.getDiscountCode());
    	  pro.setProductId(productId);
    	  p.add(pro);
		}
    	  
    	  return p;
      }
//      public List<Promotion> toPromotion(List<Pro> prl) {
//  		if (prl == null) {
//  			return null;
//  		}
//  		List<Product> pl = new ArrayList<Product>();
//  		for (ProductRequest pr : prl) {
//  			pl.add(toProduct(pr));
//  		}
//  		return pl;
//  	}
      
      
       public PromotionResponse toPromotionResponse(Promotion p) {
    	   if(p == null) {
    		   return null;
    	   }
    	   PromotionResponse pr=new PromotionResponse();
    	   pr.setPromotionId(p.getPromotionId());
    	   pr.setProduct(productService.findOne(p.getProductId()));
    	   pr.setDiscountCode(p.getDisountCode());
    	   pr.setDiscount(p.getDiscount());
    	   pr.setCreateDate(p.getCreateDate());
    	   pr.setExprieDate(p.getExprieDate());
    	   return pr;
    	   
    	   
       }
       
       public List<PromotionResponse> toPromotionResponse(List<Promotion> lp) {
    	   if(lp==null) {
    		   return null;
    	   }
    	   List<PromotionResponse> prl=new ArrayList<PromotionResponse>();
    	   for(Promotion p : lp) {
    		   prl.add(toPromotionResponse(p));
    	   }
    	   return prl;
       }

	public Page<PromotionResponse> toPromotionResponse(Page<Promotion> page) {
		// TODO Auto-generated method stub
		if (page == null)
			return null;
		List<PromotionResponse> pl = toPromotionResponse(page.getContent());
		Page<PromotionResponse> reponse = new PageImpl<PromotionResponse>(pl, page.getPageable(), page.getTotalElements());
		return reponse;
	}
       
}
