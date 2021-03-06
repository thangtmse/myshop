package com.fpt.service;

import java.util.List;

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
		Supplier oldSupplier = supplierRepository.getOne(supplier.getSupplierId());
		oldSupplier.setSupplierName(supplier.getSupplierName());
		oldSupplier.setSupplierPhone(supplier.getSupplierPhone());
		oldSupplier.setSupplierAddress(supplier.getSupplierAddress());
		// TODO Auto-generated method stub
		return supplierRepository.save(oldSupplier);
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
    public List<Supplier> findAllsup(){
    	return supplierRepository.findAll();
    }
}
