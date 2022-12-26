package com.example.les_springboot.repository;

import com.example.les_springboot.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.name=:name")
    List<Customer> findByName(@Param("name") String name);

    @Query("select c from Customer c where c.surname=:surname")
    List<Customer> findBySurname(@Param("surname")String surname);
}
