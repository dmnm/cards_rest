package org.example.cards.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.cards.auth.LoginFacade;
import org.example.cards.auth.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthEventsHandler implements LogoutSuccessHandler, AuthenticationSuccessHandler {
    @Autowired
    private LoginFacade loginFacade;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            loginFacade.invalidateToken(user.token);
            response.setHeader(TokenUtils.AUTH_TOKEN_KEY, "");
        }
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails user = (UserDetails) authentication.getPrincipal();
            response.addHeader(TokenUtils.AUTH_TOKEN_KEY, user.token);

            Cookie cookie = new Cookie(TokenUtils.AUTH_TOKEN_KEY, user.token);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            String s = "{\"token\":\"" + user.token + "\"}";
            response.getOutputStream().write(s.getBytes());
        }
    }
}
