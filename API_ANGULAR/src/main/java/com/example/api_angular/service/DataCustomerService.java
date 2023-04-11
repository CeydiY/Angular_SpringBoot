package com.example.api_angular.service;

import com.example.api_angular.model.DataCustomer;
import com.example.api_angular.repository.DataCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataCustomerService {
    @Autowired
    private DataCustomerRepository repository;
    public List<DataCustomer> dataClientList(){
        return repository.findAll();
    }
    public void saveDataClient (DataCustomer dataCustomer){
        repository.save(dataCustomer);
    }
    public DataCustomer getDataClientById(Integer id){
        return repository.findById(id).get();
    }
    public void deleteDataClient(Integer id){
        repository.deleteById(id);
    }
}
