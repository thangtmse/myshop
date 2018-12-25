package com.fpt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Report;
import com.fpt.repository.ReportRepository;

@Service
public class ReportService {
  @Autowired
  private ReportRepository reportRepository;
	public Report create(Report report) {
		// TODO Auto-generated method stub
		report.setCreatedDate(new Date());
		return reportRepository.save(report);
	}
	public Page<Report> search(Long id,Pageable pageable) {
		// TODO Auto-generated method stub
		return reportRepository.findByUserIdContaining(id, pageable);
	}

}
