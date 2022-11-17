package com.fs12.javaspringboot.security;

import com.fs12.javaspringboot.filter.CustomAuthenticationFilter;
import com.fs12.javaspringboot.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("api/v1/admin-login").permitAll();
        http.authorizeRequests().antMatchers("api/v1/token/refresh/**").permitAll();

        //http.authorizeRequests().antMatchers(GET, "/api/v1/products/**").hasAnyAuthority("PRODUCTS_READ");
        http.authorizeRequests().antMatchers(POST, "/api/v1/products").hasAnyAuthority("PRODUCTS_WRITE");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/products/**").hasAnyAuthority("PRODUCTS_WRITE");
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/products/**").hasAnyAuthority("PRODUCTS_WRITE");

        //http.authorizeRequests().antMatchers(GET, "/api/v1/orders/**").hasAnyAuthority("ORDERS_READ");
        //http.authorizeRequests().antMatchers(POST, "/api/v1/orders").hasAnyAuthority("ORDERS_WRITE");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/orders/**").hasAnyAuthority("ORDERS_WRITE");
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/orders/**").hasAnyAuthority("ORDERS_WRITE");

        //http.authorizeRequests().antMatchers(GET, "/api/v1/users/**").hasAnyAuthority("USERS_READ");
        //http.authorizeRequests().antMatchers(POST, "/api/v1/users").hasAnyAuthority("USERS_WRITE");
        //http.authorizeRequests().antMatchers(PUT, "/api/v1/users/**").hasAnyAuthority("USERS_WRITE");
        //http.authorizeRequests().antMatchers(DELETE, "/api/v1/users/**").hasAnyAuthority("USERS_WRITE");

        http.authorizeRequests().antMatchers(GET, "/api/v1/admins/**").hasAnyAuthority("ADMINS_READ");
        http.authorizeRequests().antMatchers(POST, "/api/v1/admins").hasAnyAuthority("ADMINS_WRITE");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/admins/**").hasAnyAuthority("ADMINS_WRITE");
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/admins/**").hasAnyAuthority("ADMINS_WRITE");

        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
