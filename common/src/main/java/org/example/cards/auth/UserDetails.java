package org.example.cards.auth;

import org.example.cards.annotation.Simplified;

@Simplified
public class UserDetails {
	public Long id;
	public String username;
	public String firstName;
	public String lastName;
	public String address;
	
	@Override
	public String toString() {
		return username;
	}
}
