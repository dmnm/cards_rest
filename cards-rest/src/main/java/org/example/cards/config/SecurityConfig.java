package org.example.cards.config;

import org.example.cards.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthProvider authProvider;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/api/**").authenticated()
            .and()
            .httpBasic().realmName("Login: user, Password: 123")
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder authManagerBuilder) {
		authManagerBuilder.authenticationProvider(authProvider);
	}
}
