package org.example.cards.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

public class TokenAuthenticationService {
    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
        final User user = (User) authentication.getDetails();
        response.addHeader(TokenUtils.AUTH_TOKEN_KEY, asToken(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(TokenUtils.AUTH_TOKEN_KEY);
        if (token != null) {
            final User user = asUser(token);
            if (user != null) {
                return new TokenAuthentication();
            }
        }
        return null;
    }

    private User asUser(String token) {
        // TODO: loginFacade.getUserByToken(token);
        return null;
    }

    private String asToken(User user) {
        // TODO: loginFacade.getTokenForUser(user.name OR user.id);
        return null;
    }
}
