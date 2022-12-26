package com.example.les_springboot.controllers;

import com.example.les_springboot.dto.CustomerDTO;
import com.example.les_springboot.models.Customer;
import com.example.les_springboot.repository.CustomerRepository;
import com.example.les_springboot.services.CustomerService;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @PostMapping("")
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomer() {
        List<Customer> all = customerRepository.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable int id) {
        return customerRepository.findById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Customer> patchById(@PathVariable int id,
                                              @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id).get();
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setEmail(customerDTO.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        customerRepository.deleteById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> findAll(@PathVariable String name) {
        return customerService.customerListByName("vasyl");
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<Customer>> findAllBySurname(@PathVariable String surname) {
        return customerService.customerListBySurname("");
    }
}
