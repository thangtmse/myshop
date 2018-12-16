package com.fpt.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.fpt.dto.response.PromotionResponse;
import com.fpt.entity.Promotion;
import com.fpt.service.ProductService;
@Component
public class PromotionMapper {
      @Autowired
      private ProductService productService;
      
       public PromotionResponse toPromotionResponse(Promotion p) {
    	   if(p == null) {
    		   return null;
    	   }
    	   PromotionResponse pr=new PromotionResponse();
    	   pr.setPromotionId(p.getPromotionId());
    	   pr.setProduct(productService.findOne(p.getPromotionId()));
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
