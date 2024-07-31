package com.sb.demo.response;

import com.sb.demo.enums.CustomerStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class CustomerResponse {
    private Long customerId;
    private String customerName;
    private int customerAge;
    private String customerMobileNumber;
    private String customerEmailAddress;
    private String customerAddress;
    private CustomerStatus status;
    private boolean verified;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
