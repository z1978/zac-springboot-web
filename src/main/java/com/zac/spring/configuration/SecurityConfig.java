package com.zac.spring.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private UserDetailsServiceImpl userDetailsServiceImpl;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// Beans
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public DaoAuthenticationProvider authProvider() {
//		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsServiceImpl);
//		authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//		return authProvider;
//	}

	// @Bean
	// public RememberMeServices rememberMeServices() {
	// return new CustomRememberMeServices("theKey",
	// userDetailsServiceImpl, new InMemoryTokenRepositoryImpl());
	// }

	// Override methods
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**", "/index", "/", "/register", "/submit-registration",
						"/tm/**", "/TitanMaster/**", "/eb", "/eBusiness/**")
				.permitAll().antMatchers("/adminPage/**").hasRole("ADMIN").anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().loginProcessingUrl("/perform-login")
				.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/index", true)
				.failureUrl("/login-error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authProvider());
//	}

}
