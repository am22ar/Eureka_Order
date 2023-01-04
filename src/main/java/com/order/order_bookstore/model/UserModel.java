package com.order.order_bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private String address;
}
