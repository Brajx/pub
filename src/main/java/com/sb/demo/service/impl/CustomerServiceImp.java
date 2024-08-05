package com.sb.demo.service.impl;

import com.sb.demo.model.CustomerModel;
import com.sb.demo.repository.CustomerRepository;
import com.sb.demo.request.CustomerRequest;
import com.sb.demo.response.APIResponse;
import com.sb.demo.response.CustomerResponse;
import com.sb.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sb.demo.constants.AppConstants.*;
import static com.sb.demo.mapper.CustomerMapper.modelToResponse;
import static com.sb.demo.mapper.CustomerMapper.requestToModel;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {


    private final CustomerRepository customerRepository;


    @Override
    public ResponseEntity<APIResponse> createCustomer(CustomerRequest request) {
        CustomerModel customerModel=customerRepository.save(requestToModel(request));
       return ResponseEntity.ok(
               APIResponse.builder().errorCode(SUCCESS_CODE).errorMessage(SUCCESSFULLY_STORED)
                       .responseData(modelToResponse(customerModel)).build()
       );
    }

    @Override
    public ResponseEntity<APIResponse> getAllCustomers() {
        List<CustomerModel> customerModelList = customerRepository.findAll();
        List<CustomerResponse> customerResponses=customerModelList
                .stream()
                .map(customerModel -> modelToResponse(customerModel))
                .toList();
        return ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(SUCCESS_CODE)
                        .errorMessage(SUCCESSFULLY_RETRIEVED)
                        .responseData(customerResponses)
                        .build()
        );
    }

    @Override
    public ResponseEntity<APIResponse> getByCustomerId(long customerId) {
        Optional<CustomerModel> customerModel=customerRepository.findById(customerId);
        if(customerModel.isPresent()){
            CustomerModel c=customerModel.get();
            CustomerResponse response = modelToResponse(c);
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(SUCCESS_CODE)
                            .errorMessage(SUCCESSFULLY_RETRIEVED)
                            .responseData(response)
                            .build()

            );
        }else {
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(CUSTOMER_NOT_EXISTS_CODE)
                            .errorMessage(CUSTOMER_NOT_EXISTS)
                            .responseData(List.of())
                            .build());
        }
    }

    @Override
    public ResponseEntity<APIResponse> deleteByCustomerId(long customerId) {
        Optional<CustomerModel> customerModel=customerRepository.findById(customerId);
        if(!customerModel.isPresent()){
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(CUSTOMER_NOT_EXISTS_CODE)
                            .errorMessage(CUSTOMER_NOT_EXISTS)
                            .responseData(List.of())
                            .build()
            );
        }else {
            customerRepository.deleteById(customerId);
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(SUCCESS_CODE)
                            .errorMessage(SUCCESSFULLY_DELETED)
                            .responseData(List.of())
                            .build()
            );

        }

    }

    @Override
    public ResponseEntity<APIResponse> updateCustomerDetails(long customerId, CustomerRequest request) {
        Optional<CustomerModel> customerModel=customerRepository.findById(customerId);
        if(customerModel.isPresent()){
            CustomerModel Model=customerModel.get();
            Model.setCustomerName(request.getCustomerName());
            Model.setCustomerAge(request.getCustomerAge());
            Model.setCustomerMobileNumber(request.getCustomerMobileNumber());
            Model.setCustomerEmailAddress(request.getCustomerEmailAddress());
            Model.setCustomerAddress(request.getCustomerAddress());
           Model=customerRepository.save(Model);
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(SUCCESS_CODE)
                            .errorMessage(SUCCESSFULLY_UPDATED)
                            .responseData(modelToResponse(Model))
                            .build()
            );
        }
        else {
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(CUSTOMER_NOT_EXISTS_CODE)
                            .errorMessage(CUSTOMER_NOT_EXISTS)
                            .responseData(List.of())
                            .build()
            );
        }
    }
}
