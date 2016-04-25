package org.example.cards.config;

import org.example.cards.security.AuthProvider;
import org.example.cards.security.BasicAuthFilter;
import org.example.cards.auth.LoginFacade;
import org.example.cards.security.LogoutHandler;
import org.example.cards.security.RestAuthenticationEntryPoint;
import org.example.cards.security.TokenAuthenticationFilter;
import org.example.cards.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private RestAuthenticationEntryPoint authenticationEntryPoint;

		@Autowired
		private LoginFacade loginFacade;

		protected void configure(HttpSecurity http) throws Exception {
			http
                .antMatcher("/api/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                	.addFilterBefore(new TokenAuthenticationFilter(loginFacade), BasicAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
		}
	}

	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private AuthProvider authProvider;

		@Autowired
		private LogoutHandler logoutHandler;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
            	.authorizeRequests().antMatchers("/auth/**").authenticated()
        		.and()
        			.addFilterBefore(new BasicAuthFilter(authenticationManager()), BasicAuthenticationFilter.class)
        		.httpBasic().realmName("Login: user, Password: 123")
                .and()
	                .logout()
	                    .logoutUrl("/logout")
	                    .logoutSuccessHandler(logoutHandler)
	                    .clearAuthentication(true)
	                    .deleteCookies("JSESSIONID", TokenUtils.AUTH_TOKEN_KEY)
	                    .invalidateHttpSession(true)
                .and().csrf().disable();
		}

		@Override
		protected void configure(final AuthenticationManagerBuilder authManagerBuilder) {
			authManagerBuilder.authenticationProvider(authProvider);
		}
	}
}
