package com.fpt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpt.entity.Image;
import com.fpt.service.ImageService;

@Controller
@RequestMapping("/api/image")
public class ImageProductController {
	@Autowired
	private ImageService imageService;

	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	public void download(@PathVariable("id") Long id, HttpServletResponse response) throws Exception {
		Image image = imageService.getById(id);
		File file = new File(image.getImageUrl());
		InputStream inputStream = new FileInputStream(file);
		response.setContentType("image/*");
//		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
		inputStream.close();
	}

}
