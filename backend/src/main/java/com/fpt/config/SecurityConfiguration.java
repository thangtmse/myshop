package com.fpt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fpt.security.AuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests() //
				.anyRequest().permitAll() //
				.and().addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint) //
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
