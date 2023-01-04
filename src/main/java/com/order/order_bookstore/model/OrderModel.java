package com.order.order_bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.order.order_bookstore.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Order_Details")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate localDate =LocalDate.now();
    private Long price;
    private int quantity;
    private String address;

    private Long user;
    private Long book;
    private String email;
    private boolean cancel;

    public OrderModel(Long user, Long book, Long price,String email, int quantity, String address, boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.email = email;
        this.user=user;
        this.book=book;
        this.cancel=cancel;
    }
}

