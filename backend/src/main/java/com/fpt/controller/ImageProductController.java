package com.fpt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (Exception e) {
			inputStream = null;
			// TODO: handle exception
		}

		response.setContentType("image/*");
//		response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
		inputStream.close();
	}
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public void saveImage(HttpServletResponse response) {
		byte[] imageByte=Base64.decodeBase64("");

        String directory= this.getClass().getClassLoader().getResource("").getPath()+"../../../images/";
        Image image=new Image();
        image.setImageUrl(directory);
        imageService.create(image);
   
//        new FileOutputStream(directory).write(imageByte);
//        return "success ";
	}
	
	@RequestMapping(path = "test", method = RequestMethod.GET)
	public ResponseEntity<?> test() throws Exception {
		List<Long> x =new ArrayList<Long>();
		x.add(51L);
		x.add(52L);
		return new ResponseEntity<>(imageService.findIdNotIn(x, 1L), HttpStatus.OK);
	}

}
