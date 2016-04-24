package org.example.cards.security;

import java.util.ArrayList;

import org.example.cards.auth.LoginFacade;
import org.example.cards.auth.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider implements AuthenticationProvider {
    @Autowired
    private LoginFacade loginFacade;

    @Override
    public Authentication authenticate(Authentication authentication) {
        if (!(authentication instanceof TokenAuthentication)) {
            return authentication;
        }

        String token = ((TokenAuthentication) authentication).getToken();

        UserDetails user = loginFacade.getUserByToken(token);

        if (user != null) {
            return new UsernamePasswordAuthenticationToken(user, token, new ArrayList<>());
        } else {
            throw new BadCredentialsException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (TokenAuthentication.class.isAssignableFrom(authentication));
    }

    /*
     * TokenAuthentication jwtAuthenticationToken = (TokenAuthentication) authentication; String token =
     * jwtAuthenticationToken.getToken();
     * 
     * User parsedUser = jwtUtil.parseToken(token);
     * 
     * if (parsedUser == null) { throw new JwtTokenMalformedException("JWT token is not valid"); }
     * 
     * List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());
     * 
     * return new AuthenticatedUser(parsedUser.getId(), parsedUser.getUsername(), token, authorityList);
     */

}
