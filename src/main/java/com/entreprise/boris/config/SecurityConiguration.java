package com.entreprise.boris.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.entreprise.boris.filter.CostumAuthorizationFilter;
import com.entreprise.boris.filter.CustomAuthenticationFilter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Configuration
@EnableWebSecurity
@NoArgsConstructor
@AllArgsConstructor
public class SecurityConiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;	

	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		super.configure(web);
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// In memory
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		// In Database
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
		authenticationFilter.setFilterProcessesUrl("/api/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/api/login/**").permitAll(); 
		http.authorizeRequests().antMatchers(HttpMethod.GET,"api/users/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN"); 
		http.authorizeRequests().antMatchers(HttpMethod.POST,"api/users/**").hasAnyAuthority("ROLE_ADMIN"); 
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(authenticationFilter);
		http.addFilterBefore(new CostumAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); 
	}
	
	

}
