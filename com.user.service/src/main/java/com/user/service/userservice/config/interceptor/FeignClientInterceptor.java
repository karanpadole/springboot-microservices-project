package com.user.service.userservice.config.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

//@Configuration
//@Component
//public class FeignClientInterceptor  implements RequestInterceptor {
//
//    private OAuth2AuthorizedClientManager manager;
//
//    @Override
//    public void apply(RequestTemplate template) {
//
//        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("auth0-client").principal("internal").build()).getAccessToken().getTokenValue();
//
//        template.header("Authorization", "Bearer" + token);
//    }
//}

@Configuration
public class FeignClientInterceptor implements RequestInterceptor {

    private final OAuth2AuthorizedClientManager manager;

    public FeignClientInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public void apply(RequestTemplate template) {

        String token = manager.authorize(
                OAuth2AuthorizeRequest.withClientRegistrationId("auth0-client")
                        .principal("internal")
                        .build()
        ).getAccessToken().getTokenValue();

        template.header("Authorization", "Bearer " + token);
    }
}