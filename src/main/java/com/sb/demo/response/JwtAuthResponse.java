package com.sb.demo.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private String role;
}
