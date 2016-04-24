package org.example.cards.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.cards.auth.LoginFacade;
import org.example.cards.auth.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationFilter2 extends AbstractAuthenticationProcessingFilter {
    @Autowired
    private LoginFacade loginFacade;

    protected TokenAuthenticationFilter2() {
        super("/api/**");
    }

    @Override
    public void afterPropertiesSet() {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);

        if (token == null || token.trim().isEmpty()) {
            throw new BadCredentialsException("No auth token found!");
        }

        return getAuthenticationManager().authenticate(TokenAuthentication.get(token));
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(TokenUtils.AUTH_TOKEN_KEY);
        if (header != null) {
            return header;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TokenUtils.AUTH_TOKEN_KEY.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    private Authentication authenticationFromToken(String token) {
        UserDetails user = loginFacade.getUserByToken(token);

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, token, new ArrayList<>());
        } else {
            throw new BadCredentialsException("Username not found");
        }
    }

    /*@Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }*/
}
