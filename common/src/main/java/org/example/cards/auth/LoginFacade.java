package org.example.cards.auth;

public interface LoginFacade {

    /**
     * @return UserDetails - details of authenticated user or <code>null</code> if no user found
     */
    UserDetails login(String username, String password);

    /**
     * @return UserDetails - details of authenticated user or <code>null</code> if no user found with specified token
     */
    UserDetails getUserByToken(String token);

    void logout(Long userId);

    void logout(String username);

    void invalidateToken(String token);
}
