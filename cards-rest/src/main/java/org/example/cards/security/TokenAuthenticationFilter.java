package org.example.cards.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

//@Component
public class TokenAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        SecurityContext cxt = SecurityContextHolder.getContext();
        //cxt.setAuthentication(authService.getAuthentication((HttpServletRequest) request));
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String header = httpRequest.getHeader(TokenUtils.AUTH_TOKEN_KEY);
        Principal p = httpRequest.getUserPrincipal();
        Cookie[] cookies = httpRequest.getCookies();
        Authentication authentication =  cxt.getAuthentication();

        filterChain.doFilter(request, response);

        //cxt.setAuthentication(null);
    }

}
