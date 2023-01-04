package com.order.order_bookstore.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDto {
    public String message;
    public Object data;
    public String token;
}
