package org.example.cards.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.example.cards.annotation.Simplified;
import org.example.cards.auth.LoginFacade;
import org.example.cards.auth.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean {
	private LoginFacade loginFacade;

	public TokenAuthenticationFilter(LoginFacade loginFacade) {
		this.loginFacade = loginFacade;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		String token = getToken((HttpServletRequest) request);

		SecurityContext cxt = SecurityContextHolder.getContext();
		if (token != null && !token.trim().isEmpty()) {
			authenticate(token, cxt);
		}

		filterChain.doFilter(request, response);
		cxt.setAuthentication(null); // per request authentication
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

	@Simplified
	// this should invoke some auth provider within auth manager
	private void authenticate(String token, SecurityContext cxt) {
		UserDetails user = loginFacade.getUserByToken(token);
		if (user != null) {
			cxt.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.token, new ArrayList<>()));
		}
	}

}
