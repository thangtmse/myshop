package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.entity.Product;
import com.fpt.mapper.ProductMapper;
import com.fpt.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductMapper productMapper;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> findProducts(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Pageable pageable) {
		Page<Product> pd = productService.findByproductNameContaining(name, pageable);
		return new ResponseEntity<>(productMapper.toProductResponse(pd), HttpStatus.OK);
	}
//	public String findProducts(Model model,
//			@RequestParam(name = "name", required = false, defaultValue = "") String name, Pageable pageable) {
//		Page<Product> pd = productService.findByproductNameContaining(name, pageable);
//		model.addAttribute("products", productMapper.toProductResponse(pd));
//		return "home";
//	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
		return new ResponseEntity<>(productMapper.toProductResponse(productService.findOne(id)), HttpStatus.OK);
	}
//
//	@RequestMapping(path = "", method = RequestMethod.POST)
//	public ResponseEntity<?> create(@RequestBody ProductRequest productRequest) {
//		return new ResponseEntity<>(
//				productMapper.toProductResponse(productService.create(productMapper.toProduct(productRequest))),
//				HttpStatus.CREATED);
//	}
//
//	@RequestMapping(path = "{id}", method = RequestMethod.PUT)
//	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
//		Product product = productMapper.toProduct(productRequest);
//		product.setProductId(id);
//		return new ResponseEntity<>(productMapper.toProductResponse(productService.update(product)), HttpStatus.OK);
//	}
//
//	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<?> delete(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
//		Product product = productMapper.toProduct(productRequest);
//		product.setProductId(id);
//		return new ResponseEntity<>(productMapper.toProductResponse(productService.delete(product.getProductId())),
//				HttpStatus.OK);
//	}

}