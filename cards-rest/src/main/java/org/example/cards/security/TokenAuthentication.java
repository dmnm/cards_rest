package org.example.cards.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class TokenAuthentication implements Authentication {
    private static final long serialVersionUID = 4804962036791492273L;

    private String token;
    private boolean authenticated;

    public String getToken() {
        return token;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean val) {
        this.authenticated = val;
    }

    public static TokenAuthentication get(String token) {
        TokenAuthentication auth = new TokenAuthentication();
        auth.token = token;
        return auth;
    }

}