package com.example.e3stpavel.knorders.dao;

import com.example.e3stpavel.knorders.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
