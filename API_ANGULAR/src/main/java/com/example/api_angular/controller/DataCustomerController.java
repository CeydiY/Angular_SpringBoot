package com.example.api_angular.controller;

import com.example.api_angular.model.DataCustomer;
import com.example.api_angular.repository.DataCustomerRepository;
import com.example.api_angular.service.DataCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<DataCustomer> getDataClient(@PathVariable String id){
        try{
            DataCustomer dataCustomer = dataCustomerService.getDataClientById(id);
            return ResponseEntity.ok(dataCustomer);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/dataclient",consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE )
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
    @PutMapping(value = "/dataclient/{id}")
    public ResponseEntity<?> editDataClient(@RequestBody DataCustomer dataCustomer, @PathVariable String id){
        DataCustomer newDataClient = dataCustomerService.getDataClientById(id);

        if(newDataClient == null){
            return new ResponseEntity<DataCustomer>(HttpStatus.NOT_FOUND);
        }

        try{
            newDataClient.setFirstName(dataCustomer.getFirstName());
            newDataClient.setLastName(dataCustomer.getLastName());
            newDataClient.setName(dataCustomer.getName());
            newDataClient.setAddress(dataCustomer.getAddress());

            dataCustomerService.saveDataClient(newDataClient);
            return new ResponseEntity<DataCustomer>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<DataCustomer>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dataclient/{id}")
    public ResponseEntity<DataCustomer> deleteProduct(@PathVariable String id){
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
