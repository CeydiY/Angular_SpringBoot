package com.example.api_angular.repository;

import com.example.api_angular.model.DataCustomer;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DataCustomerRepository extends JpaRepository<DataCustomer, Integer> {
    @Query("SELECT d FROM DataCustomer d WHERE d.email = :email AND d.username = :username")
    DataCustomer findEmailAndUsername (String email, String username);
    @Transactional
    void deleteByusername(String username);

    @Transactional
    DataCustomer findByusername(String username);
}
