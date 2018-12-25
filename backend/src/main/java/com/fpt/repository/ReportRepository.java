package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
 
	Page<Report> findByUserIdContaining(Long id,Pageable pageable);
        
}
