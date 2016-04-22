package org.example.cards.auth;

public interface LoginFacade {

	/**
	 * @return UserDetails - details of authenticated user or <code>null</code>
	 *         if no user found
	 */
	UserDetails getUserDetails(String username, String password);
}
