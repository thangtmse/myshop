package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fpt.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageProductController {
	@Autowired
	private ImageService imageService;

	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@RequestParam(name = "id", required = false) Long id) {
		return new ResponseEntity<>(imageService.getListImage(id), HttpStatus.OK);
	}

}
