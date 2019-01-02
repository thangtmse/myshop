package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.dto.request.ProductRequest;
import com.fpt.entity.Product;
import com.fpt.mapper.ProductMapper;
import com.fpt.service.ProductService;

@Controller
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> findProducts(Pageable pageable,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "categoryid", required = false) Long category,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max,
			@RequestParam(value = "deleted", required = false) Boolean deleted) throws Exception {
		if(deleted == null) deleted = false;
		Page<Product> pd = productService.findProducts(category, name, min, max, deleted, pageable);
		return new ResponseEntity<>(productMapper.toProductResponse(pd), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/slide", method = RequestMethod.GET)
	public ResponseEntity<?> getSlide(Pageable pageable,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "categoryid", required = false) Long category,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max) throws Exception {

		Page<Product> pd = productService.getSlide(category, name, min, max, pageable);
		return new ResponseEntity<>(productMapper.toProductResponse(pd), HttpStatus.OK);
	}
	@RequestMapping(path = "/deleted", method = RequestMethod.GET)
	public ResponseEntity<?> getDeletedProducts(Pageable pageable){
		Page<Product> pd = productService.getDeletedProduct( pageable);
		return new ResponseEntity<>(productMapper.toProductResponse(pd), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
		return new ResponseEntity<>(productMapper.toProductResponse(productService.findOne(id)), HttpStatus.OK);
	}

	@RequestMapping(path = "/acept", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(
				productMapper.toProductResponse(productService.create(productMapper.toProduct(productRequest))),
				HttpStatus.CREATED);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
		Product product = productMapper.toProduct(productRequest);
		product.setProductId(id);
		product.setDeleted(false);
		return new ResponseEntity<>(productMapper.toProductResponse(productService.update(product)), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		// Product product = productMapper.toProduct(productRequest);

		return new ResponseEntity<>(productMapper.toProductResponse(productService.delete(id)), HttpStatus.OK);
	}

	@RequestMapping(path = "getRank", method = RequestMethod.GET)
	public ResponseEntity<?> getRankOfPrice() {
		return new ResponseEntity<>(productService.getRankOfPrice(), HttpStatus.OK);
	}

}
