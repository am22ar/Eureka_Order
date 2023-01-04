package com.order.order_bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @NotNull(message = "Price cannot be null")
    public long price;

    @Email
    public String email;
    @NotNull(message = "Quantity can not be null!")
    public int quantity;
    @NotNull(message = "Address can not be null!")
    public String address;
    @NotNull(message = "UserId can not be null")
    public Long userId;
    @NotNull(message = "BookId can not be null")
    public Long bookId;
    public boolean cancel;
}
