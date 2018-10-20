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

import com.fpt.entity.Category;
import com.fpt.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Pageable pageable) {
		return new ResponseEntity<>(categoryService.findByNameContaining(name, pageable), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(categoryService.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Category category) {
		category.setCategoryID(id);
		return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
	}

}
