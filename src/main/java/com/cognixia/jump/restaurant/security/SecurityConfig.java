package com.cognixia.jump.restaurant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/restaurant/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/restaurant/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/restaurant/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/restaurant/**").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/review/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/review/**").hasAuthority("USER")
		.antMatchers(HttpMethod.PUT, "/api/review/**").hasAuthority("USER")
		.antMatchers(HttpMethod.DELETE, "/api/review/**").hasAuthority("USER")
		.antMatchers("/api/user").hasAuthority("USER")
		.antMatchers(HttpMethod.GET, "/api/user/**").hasAuthority("USER")
		.antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/api/user/**").hasAuthority("USER")
		.antMatchers(HttpMethod.DELETE, "/api/user/**").hasAuthority("USER")
		.antMatchers("/api/admin").hasAuthority("ADMIN")//change to ADMIN
		.antMatchers("/api/admin/**").hasAuthority("ADMIN")
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}

}
