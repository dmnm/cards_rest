package org.example.cards.config;

import org.example.cards.security.AuthProvider;
import org.example.cards.security.AuthEventsHandler;
import org.example.cards.security.RestAuthenticationEntryPoint;
import org.example.cards.security.TokenAuthProvider;
import org.example.cards.security.TokenAuthenticationFilter;
import org.example.cards.security.TokenAuthenticationFilter2;
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
public class SecurityConfig /* extends WebSecurityConfigurerAdapter */ {
//    @Autowired
//    private AuthProvider authProvider;

//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
/*        http.antMatcher("/api/**").authorizeRequests().anyRequest()
                .authenticated().and().formLogin().and().logout()
                .clearAuthentication(true).deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).logoutSuccessUrl("/")
                .logoutUrl("/logout").and().exceptionHandling()
                .authenticationEntryPoint(restAuthEntryPoint).and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
*/
/*        http
        .antMatcher("/api/**")                               
        .authorizeRequests()
            .anyRequest()
            .authenticated()
            //.hasRole("USER")
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable()
        //.httpBasic()
        ;
*/          
        
/*        http.authorizeRequests() .antMatchers("/auth/**").authenticated()
          .and() .httpBasic().realmName("Login: user, Password: 123") .and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
          STATELESS) .and() .csrf().disable();
         
    }
*/
/*    @Override
    protected void configure(
            final AuthenticationManagerBuilder authManagerBuilder) {
        authManagerBuilder.authenticationProvider(authProvider);
    }
*/
    
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        auth
            .inMemoryAuthentication()
                .withUser("user").password("123").roles("USER").and()
                .withUser("admin").password("admin").roles("USER", "ADMIN");
    }*/

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private RestAuthenticationEntryPoint authenticationEntryPoint;

        @Autowired
        private TokenAuthenticationFilter2 tokenAuthenticationFilter;

        @Autowired
        private TokenAuthProvider authProvider;

        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/**")
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        }
        
        @Override
        protected void configure(final AuthenticationManagerBuilder authManagerBuilder) {
            authManagerBuilder.authenticationProvider(authProvider);
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthProvider authProvider;

        @Autowired
        private AuthEventsHandler handler;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests().antMatchers("/auth/**").authenticated()
                .and().formLogin().successHandler(handler)
                //.and().httpBasic().realmName("Login: user, Password: 123")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(handler)
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
