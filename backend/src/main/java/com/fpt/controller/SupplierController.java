package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.entity.Supplier;
import com.fpt.service.SupplierService;

@Controller
@RequestMapping("/api/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(supplierService.findAllsup(),HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> getSupplier(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Pageable pageable) {
		return new ResponseEntity<>(supplierService.findByNameContaining(name, pageable), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findSupplierById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(supplierService.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Supplier supplier) {
		return new ResponseEntity<>(supplierService.create(supplier), HttpStatus.CREATED);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Supplier supplier) {
		supplier.setSupplierId(id);
		return new ResponseEntity<>(supplierService.update(supplier), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<>(supplierService.delete(id), HttpStatus.OK);
	}
}
