package com.sb.demo.mapper;

import com.sb.demo.enums.CustomerStatus;
import com.sb.demo.model.CustomerModel;
import com.sb.demo.request.CustomerRequest;
import com.sb.demo.response.CustomerResponse;
import com.sb.demo.utils.AppUtils;

public class CustomerMapper {

   public static CustomerResponse modelToResponse(CustomerModel customerModel){
       return CustomerResponse.builder()
               .customerId(customerModel.getCustomerId())
               .customerName(customerModel.getCustomerName())
               .customerAge(customerModel.getCustomerAge())
               .customerMobileNumber(customerModel.getCustomerMobileNumber())
               .customerEmailAddress(customerModel.getCustomerEmailAddress())
               .customerAddress(customerModel.getCustomerAddress())
               .status(customerModel.getStatus())
               .createdDate(customerModel.getCreateDate())
               .updatedDate(customerModel.getUpdateDate())
               .build();
    }

    public static CustomerModel requestToModel(CustomerRequest request){
        return CustomerModel.builder()
                .customerName(request.getCustomerName())
                .customerPassword(AppUtils.generatePassword())
                .customerAge(request.getCustomerAge())
                .customerMobileNumber(request.getCustomerMobileNumber())
                .customerEmailAddress(request.getCustomerEmailAddress())
                .customerAddress(request.getCustomerAddress())
                .status(CustomerStatus.INACTIVE)
                .customerOtp(AppUtils.generateOtp())
                .build();
    }





}

