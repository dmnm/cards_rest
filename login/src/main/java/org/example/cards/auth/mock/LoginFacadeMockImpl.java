package org.example.cards.auth.mock;

import org.example.cards.auth.LoginFacade;
import org.example.cards.auth.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginFacadeMockImpl implements LoginFacade {

	private final UserDetails mockUser;

	@Override
	public UserDetails getUserDetails(String username, String password) {
		if (mockUser.username.equals(username) && "123".equals(password)) { // TODO: add MD5
			return mockUser;
		}
		return null;
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
