package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	public Page<Supplier> findBySupplierNameContainingAndDeleted(String q, Boolean deleted, Pageable pageable);
}
