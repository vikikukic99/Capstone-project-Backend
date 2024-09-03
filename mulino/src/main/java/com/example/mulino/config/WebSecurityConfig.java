package com.example.mulino.config;


import com.example.mulino.security.RestAuthenticationEntryPoint;
import com.example.mulino.security.TokenAuthenticationFilter;
import com.example.mulino.service.CustomUserDetailsService;
import com.example.mulino.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Autowired
	private TokenUtils tokenUtils;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// sve neautentifikovane zahteve obradi uniformno i posalji 401 gresku
		http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
		http.authorizeRequests().antMatchers("/api/auth/**").permitAll()		// /auth/**
				.antMatchers("/h2-console/**").permitAll()	// /h2-console/** ako se koristi H2 baza)
				.antMatchers("/api/menuItems").permitAll()
				.antMatchers("/api/menuItems/**").permitAll()
				.antMatchers("/api/reservation").permitAll()
				.antMatchers("/api/reservation/**").permitAll()
				.antMatchers("/api/subscribe/**").permitAll()

				.anyRequest().authenticated().and()
				.cors().and()
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils,  userDetailsService()), BasicAuthenticationFilter.class);

		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authenticationProvider(authenticationProvider());

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers(HttpMethod.POST, "/api/auth/login")

				.antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico",
						"/**/*.html", "/**/*.css", "/**/*.js");
	}
}
