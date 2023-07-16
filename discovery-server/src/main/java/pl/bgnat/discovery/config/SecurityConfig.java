package pl.bgnat.discovery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
	@Value("${app.eureka.username}")
	private String username;
	@Value("${app.eureka.password}")
	private String password;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(httpSecurityCsrfConfigurer ->
				httpSecurityCsrfConfigurer.disable())
				.authorizeHttpRequests(
						request -> request.anyRequest()
								.authenticated()
				).httpBasic(s -> s.init(httpSecurity));
		return httpSecurity.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public UserDetailsService users() {
		// The builder will ensure the passwords are encoded before saving in memory
		User.UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails user = users
				.username(username)
				.password(password)
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
