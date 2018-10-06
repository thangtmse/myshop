package com.fpt.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fpt.entity.Product;


public interface ProductRepository extends JpaRepository<Product, BigInteger>{
}
