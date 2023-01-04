package com.order.order_bookstore.service;

import com.order.order_bookstore.dto.OrderDto;
import com.order.order_bookstore.model.OrderModel;

import java.util.List;

public interface IOrderService {
    public OrderModel placeOrder(OrderDto orderDto);
    public List<OrderModel> getAllOrders();
    public OrderModel getOrderById(long orderId);
    public OrderModel updateRecordById(long orderId, OrderDto orderDto);
    public OrderModel cancelOrderById(long orderId, OrderDto orderDto);
}
