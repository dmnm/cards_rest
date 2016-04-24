package org.example.cards.auth.mock;

import java.util.UUID;

import org.example.cards.auth.LoginFacade;
import org.example.cards.auth.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginFacadeMockImpl implements LoginFacade {

    private final UserDetails mockUser;

    @Override
    public UserDetails login(String username, String password) {
        if (mockUser.username.equals(username) && "123".equals(password)) { // TODO: add MD5
            mockUser.token = UUID.randomUUID().toString();
            return mockUser;
        }
        return null;
    }

    @Override
    public UserDetails getUserByToken(String token) {
        if (token != null && token.equals(mockUser.token)) {
            return mockUser;
        }
        return null;
    }

    @Override
    public void logout(Long userId) {
        mockUser.token = null;
    }

    @Override
    public void logout(String username) {
        mockUser.token = null;
    }

    @Override
    public void invalidateToken(String token) {
        mockUser.token = null;
    }

    {
        mockUser = new UserDetails();
        mockUser.id = 42L;
        mockUser.username = "user";
        mockUser.firstName = "Solid";
        mockUser.lastName = "Gof";
        mockUser.address = "322223 Mulholland Drive, Inland Empire";
    }
}
