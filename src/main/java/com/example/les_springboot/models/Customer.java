package com.example.les_springboot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private int id;
    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
