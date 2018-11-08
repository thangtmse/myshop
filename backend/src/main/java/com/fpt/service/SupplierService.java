package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fpt.entity.Supplier;
import com.fpt.repository.SupplierRepository;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	public Supplier findOne(Long id) {
		return supplierRepository.getOne(id);
	}

	public Supplier update(Supplier supplier) {
		supplier = supplierRepository.getOne(supplier.getSupplierId());
		// TODO Auto-generated method stub
		return supplierRepository.save(supplier);
	}

	public Supplier create(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierRepository.save(supplier);
	}

	public Supplier delete(Long id) {
		Supplier supplier = supplierRepository.getOne(id);
		supplier.setDeleted(true);
		// TODO Auto-generated method stub
		return supplierRepository.save(supplier);
	}

	public Page<Supplier> findByNameContaining(String q, Pageable pageable) {
		return supplierRepository.findBySupplierNameContainingAndDeleted(q, false, pageable);
	}

}
