package com.example.api_angular.controller;

import com.example.api_angular.model.DataCustomer;
import com.example.api_angular.repository.DataCustomerRepository;
import com.example.api_angular.service.DataCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class DataCustomerController {
    private final DataCustomerService dataCustomerService;
    private final DataCustomerRepository dataCustomerRepository;

    public DataCustomerController(DataCustomerService dataCustomerService, DataCustomerRepository dataCustomerRepository) {
        this.dataCustomerService = dataCustomerService;
        this.dataCustomerRepository = dataCustomerRepository;
    }

    @GetMapping("/dataclient")
    public ResponseEntity<List<DataCustomer>> dataClientList(){
        List<DataCustomer> customerList = dataCustomerService.dataClientList();
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/dataclient/{id}")
    public ResponseEntity<DataCustomer> getDataClient(@PathVariable Integer id){
        try{
            DataCustomer dataCustomer = dataCustomerService.getDataClientById(id);
            return ResponseEntity.ok(dataCustomer);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dataclient")
    public ResponseEntity<Object> newDataClient (@RequestBody DataCustomer dataCustomer) throws Exception{
        DataCustomer newDataClient = new DataCustomer();

        newDataClient = dataCustomerRepository.findEmailAndUsername(dataCustomer.getEmail(), dataCustomer.getUsername());

        if(newDataClient != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        try{
            dataCustomerService.saveDataClient(dataCustomer);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
    @PutMapping("/dataclient/{id}")
    public ResponseEntity<?> editDataClient(@RequestBody DataCustomer dataCustomer, @PathVariable Integer id){
        DataCustomer newDataClient = dataCustomerService.getDataClientById(id);

        if(newDataClient == null){
            return new ResponseEntity<DataCustomer>(HttpStatus.NOT_FOUND);
        }

        try{
            newDataClient.setId(newDataClient.getId());
            newDataClient.setFirstName(dataCustomer.getFirstName());
            newDataClient.setLastName(dataCustomer.getLastName());
            newDataClient.setName(dataCustomer.getName());
            newDataClient.setAge(dataCustomer.getAge());
            newDataClient.setAddress(dataCustomer.getAddress());
            newDataClient.setGender(dataCustomer.getGender());
            newDataClient.setCountry(dataCustomer.getCountry());
            newDataClient.setUsername(dataCustomer.getUsername());
            newDataClient.setEmail(dataCustomer.getEmail());
            newDataClient.setPassword(dataCustomer.getPassword());

            dataCustomerService.saveDataClient(newDataClient);
            return new ResponseEntity<DataCustomer>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<DataCustomer>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dataclient/{id}")
    public ResponseEntity<DataCustomer> deleteProduct(@PathVariable Integer id){
        DataCustomer newDataClient = dataCustomerService.getDataClientById(id);

        if(newDataClient == null){
            return new ResponseEntity<DataCustomer>(HttpStatus.NOT_FOUND);
        }

        try{
            dataCustomerService.deleteDataClient(id);
            return new ResponseEntity<DataCustomer>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<DataCustomer>(HttpStatus.NOT_FOUND);
        }
    }
}
