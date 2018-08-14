package com.example.demo.security;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*import org.springframework.cache.support.SimpleCacheManager;
import java.util.Collections;
import org.springframework.cache.concurrent.ConcurrentMapCache;*/



@Configuration
@EnableWebSecurity
/*@EnableCaching*/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
/*	 @Bean
	    public CacheManager cacheManager() {
	        SimpleCacheManager cacheManager = new SimpleCacheManager();
	        cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("users")));

	        return cacheManager;
	 }
	*/
/*	
	  @Autowired
	    private TokenAuthenticationService userDetailsService;
	  
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	
	
	*/
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  
	 /* http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
      .and()
      .csrf()
      .disable()
      .authorizeRequests()
      .anyRequest()
      .permitAll();
	 */
	  http.authorizeRequests().antMatchers("/").permitAll().and()
      .authorizeRequests().antMatchers("/console/**").permitAll();
	  http.headers().frameOptions().disable();
 
    http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  /*
	  auth.userDetailsService(userDetailsService);*/
	  
    // Create a default account
    auth.inMemoryAuthentication()
        .withUser("admin")
        .password("{noop}password")
        .roles("ADMIN");
  }
  
  
}
