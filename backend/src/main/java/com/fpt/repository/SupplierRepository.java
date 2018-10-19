package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
