package com.eenp.bookmaster.api.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.eenp.bookmaster.api.repository.UserRepository;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private UserRepository userRepository;
	//com.eenp.bookmaster.api.entity.User result = new com.eenp.bookmaster.api.entity.User();
	
	@Bean
	InMemoryUserDetailsManager userDetailsManager(){
		List<UserDetails> users = userRepository.findAll().stream()
	            .map(user -> User.builder()
	                .username(user.getUsuario())
	                .password("{bcrypt}" + user.getClave())
	                .roles("USER")
	                .build())
	            .collect(Collectors.toList());

	    return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer -> configurer
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/user/todos").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/user/**").hasRole("USER")
			.requestMatchers(HttpMethod.POST,"/api/bookmaster/user/create").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/author/todos").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/author/create").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/book/todos").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/publisher/todos").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/publisher/create").hasRole("USER")
			.requestMatchers(HttpMethod.GET,"/api/bookmaster/client/todos").hasRole("USER")
			.requestMatchers(HttpMethod.POST,"/api/bookmaster/client/create").hasRole("USER")
			.requestMatchers(HttpMethod.PUT,"/api/bookmaster/user/update").hasRole("USER")

            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/v3/api-docs/**").permitAll()
			);
		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf->csrf.disable());
		return http.build();
	}

}
