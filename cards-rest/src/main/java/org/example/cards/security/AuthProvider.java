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
public class AuthProvider implements AuthenticationProvider {
	@Autowired
	private LoginFacade loginFacade;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		UserDetails user = loginFacade.login(username, password);

		if (user != null) {
			return new UsernamePasswordAuthenticationToken(user, password, new ArrayList<>());
		} else {
			throw new BadCredentialsException("User not found");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
