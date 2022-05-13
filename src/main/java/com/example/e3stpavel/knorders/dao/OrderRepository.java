package com.example.e3stpavel.knorders.dao;

import com.example.e3stpavel.knorders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findAllBySubmissionDate(Date submissionDate);
}
