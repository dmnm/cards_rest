package org.example.cards.api;

import java.security.Principal;

import org.example.cards.annotation.UserId;
import org.example.cards.auth.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

	@RequestMapping("/api")
	public String[] test(Principal p, @UserId Long userId, @AuthenticationPrincipal UserDetails u) {
		Object o = SecurityContextHolder.getContext().getAuthentication();
		return new String[]{"api! " + userId};
	}
	
	@RequestMapping("/")
    public String[] testRoot(Principal p, @UserId Long userId, @AuthenticationPrincipal UserDetails u) {
        Object o = SecurityContextHolder.getContext().getAuthentication();
        return new String[]{"Root " + userId};
    }
	
	@RequestMapping("/auth")
    public String[] testAuth(Principal p, @UserId Long userId, @AuthenticationPrincipal UserDetails u) {
        Object o = SecurityContextHolder.getContext().getAuthentication();
        return new String[]{"Auth " + userId};
    }
}
