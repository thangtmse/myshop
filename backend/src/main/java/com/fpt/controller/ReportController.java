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

import com.fpt.entity.Report;
import com.fpt.service.ReportService;

@Controller
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Report report) {
		return new ResponseEntity<>(reportService.create(report), HttpStatus.CREATED);
	}
    @RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<?> getReport(@PathVariable Long id,Pageable pageable) {
		return new ResponseEntity<>(reportService.search(id,pageable), HttpStatus.OK);
	}
}
