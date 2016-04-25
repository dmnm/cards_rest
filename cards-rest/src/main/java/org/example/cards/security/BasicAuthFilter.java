package org.example.cards.security;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.cards.auth.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class BasicAuthFilter extends BasicAuthenticationFilter {

	public BasicAuthFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails user = (UserDetails) authentication.getPrincipal();
			response.addHeader(TokenUtils.AUTH_TOKEN_KEY, user.token);

			Cookie cookie = new Cookie(TokenUtils.AUTH_TOKEN_KEY, user.token);
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
		}
	}
}