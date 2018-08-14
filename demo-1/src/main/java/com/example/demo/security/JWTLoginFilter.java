package com.example.demo.security;

import java.io.IOException;
import java.util.Collections;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.Token;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	 JWTLoginFilter(String url, AuthenticationManager authManager) {
		    super(new AntPathRequestMatcher(url));
		    setAuthenticationManager(authManager);
		  }

		  @Override
		  public Authentication attemptAuthentication(
		      HttpServletRequest req, HttpServletResponse res)
		      throws AuthenticationException, IOException, ServletException {
		 UserDetails creds = new ObjectMapper()
		        .readValue(req.getInputStream(), UserDetails.class);
		    return getAuthenticationManager().authenticate(
		        new UsernamePasswordAuthenticationToken(
		            creds.getUsername(),
		            creds.getPassword(),
		            Collections.emptyList()//collect empty entity
		        )
		    );
		  }

		  @Override
		  protected void successfulAuthentication(
		      HttpServletRequest req,
		      HttpServletResponse res, FilterChain chain,
		      Authentication auth) throws IOException, ServletException {
		    TokenAuthenticationService
		        .addAuthentication(res, auth.getName());
		  }
		
}
