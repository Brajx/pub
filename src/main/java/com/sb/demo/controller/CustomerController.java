package com.sb.demo.controller;

import com.sb.demo.request.CustomerRequest;
import com.sb.demo.response.APIResponse;
import com.sb.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCustomer(@RequestBody CustomerRequest request){
        return customerService.createCustomer(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
   // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getAll")
    public ResponseEntity<APIResponse> getAllCustomer(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/getById/{customerId}")
    public ResponseEntity<APIResponse> getCustomerById(@PathVariable long customerId){
        return customerService.getByCustomerId(customerId);
    }

    @DeleteMapping("/deleteById/{customerId}")
    public ResponseEntity<APIResponse> deleteByCustomerId(@PathVariable long customerId){
        return customerService.deleteByCustomerId(customerId);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<APIResponse> updateCustomer(@PathVariable long customerId,@RequestBody CustomerRequest request){
        return customerService.updateCustomerDetails(customerId,request);
    }

}
