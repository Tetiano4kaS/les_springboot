package com.example.les_springboot.controllers;

import com.example.les_springboot.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class MainController {
    private List<Customer> customers = new ArrayList<>();

    public MainController() {
        customers.add(new Customer(1, "Vasya"));
        customers.add(new Customer(2, "Olya"));
        customers.add(new Customer(3, "Oksana"));
    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers() {
        ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<>(this.customers, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("customers")
    public ResponseEntity<List<Customer>> addCustomer(@RequestBody Customer customer) {
        this.customers.add(customer);
        return new ResponseEntity<>(this.customers, HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Customer customer = this.customers.get(id - 1);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable int id) {
        this.customers.remove(id - 1);
        return new ResponseEntity<>(this.customers, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> changeCustomer(@PathVariable int id, @RequestBody Customer customer) {
        Customer cust = customers.stream()
                .filter(customer1 -> customer1.getId() == id)
                .findFirst()
                .get();
        int indexOf = customers.indexOf(cust);
        customers.set(indexOf, customer);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @PatchMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        for (Customer customer1 : customers) {
            if (customer1.getId() == id) {
                customer1.setId(customer.getId());
                customer1.setName(customer.getName());
            }

        }
    }
}
