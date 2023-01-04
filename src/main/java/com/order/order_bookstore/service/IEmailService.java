package com.order.order_bookstore.service;

import com.order.order_bookstore.dto.ResponseDto;
import com.order.order_bookstore.model.EmailModel;
import org.springframework.http.ResponseEntity;

public interface IEmailService {
    public ResponseEntity<ResponseDto> sendEmail(EmailModel emailModel);
}
