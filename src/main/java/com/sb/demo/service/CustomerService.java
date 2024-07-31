package com.sb.demo.service;

import com.sb.demo.request.CustomerRequest;
import com.sb.demo.response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<APIResponse> createCustomer(CustomerRequest request);
    ResponseEntity<APIResponse> getAllCustomers();
    ResponseEntity<APIResponse> getByCustomerId(long customerId);
    ResponseEntity<APIResponse> deleteByCustomerId(long customerId);
    ResponseEntity<APIResponse> updateCustomerDetails(long customerId, CustomerRequest request);

}
