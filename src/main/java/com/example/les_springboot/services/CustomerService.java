package com.example.les_springboot.services;

import com.example.les_springboot.models.Customer;
import com.example.les_springboot.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;


    public void save(Customer customer){
        if (customer.getId()>0){
            customerRepository.save(customer);
        }else {
            throw new RuntimeException();
        }

    }
    public ResponseEntity<List<Customer>> customerListByName(String name){
        if (name!=null && !name.isBlank()){
            return new ResponseEntity<>(customerRepository.findByName(name), HttpStatusCode.valueOf(200));
        }
        else {
            throw new RuntimeException();
        }
    }
    public ResponseEntity<List<Customer>> customerListBySurname(String surname) {
        if (surname != null && !surname.isBlank()) {
            return new ResponseEntity<>(customerRepository.findByName(surname), HttpStatusCode.valueOf(200));
        } else {
            throw new RuntimeException();
        }
    }
}
