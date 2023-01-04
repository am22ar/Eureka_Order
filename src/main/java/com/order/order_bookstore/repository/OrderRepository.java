package com.order.order_bookstore.repository;

import com.order.order_bookstore.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long> {
}
