package com.training.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.backend.entities.OrderQuestion;

public interface OrderRepository extends JpaRepository<OrderQuestion, Long> {

}
