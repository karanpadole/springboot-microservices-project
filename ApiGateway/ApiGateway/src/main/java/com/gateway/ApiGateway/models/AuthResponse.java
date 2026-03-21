package com.gateway.ApiGateway.models;

import lombok.*;

import java.util.Collection;



//this class is not used currently
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private  String userId;

    private  String accessToken;

    private String refreshToken;

    private long expireAt;

    private Collection<String> authorities;
}
