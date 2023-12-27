package com.travel.rotalocal.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

    private static final String AUTH_KEY_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-TOKEN";

    public static Authentication getAuthentication(HttpServletRequest request) {
        
        if (request.getRequestURI().equals("/autenticar")) {
            return new ApiKeyAuthentication("", AuthorityUtils.NO_AUTHORITIES);
        }

        String apiKey = request.getHeader(AUTH_KEY_HEADER_NAME);
        String apiToken = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || apiToken == null) {
            throw new BadCredentialsException("Invalid API crendentials");
        }

        String token = Tokens.getInstance().tokensList.get(Long.parseLong(apiKey));
        if (token == null || !token.equals(apiToken)) {
            throw new BadCredentialsException("Invalid API crendentials");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
