package com.fpt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public ResponseEntity<?> findAll()
	{
		return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
	}
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			Pageable pageable) {
		return new ResponseEntity<>(categoryService.findByNameContaining(name, pageable), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(categoryService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(path = "except/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findCategoryParent(@PathVariable("id") Long id) {
		return new ResponseEntity<>(categoryService.listCategoryParent(id), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Category category) {
		category.setCategoryId(id);
		return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<>(categoryService.delete(id), HttpStatus.OK);
	}

	@RequestMapping(path = "{id}/image", method = RequestMethod.GET)
	public void getImage(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
		Category cat = categoryService.findOne(id);
		File file = new File(cat.getImageurl());
		InputStream inputStream = new FileInputStream(file);
		response.setContentType("image/*");
//		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
		inputStream.close();
	}

}
