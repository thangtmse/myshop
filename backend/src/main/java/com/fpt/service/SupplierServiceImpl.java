package com.fpt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.entity.Supplier;
import com.fpt.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public Supplier findOne(Long id) {
		// TODO Auto-generated method stub
		return supplierRepository.getOne(id);
	}

}
