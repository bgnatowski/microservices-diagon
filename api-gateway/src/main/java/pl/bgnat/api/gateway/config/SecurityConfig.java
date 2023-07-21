package pl.bgnat.api.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuerUri;

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
		serverHttpSecurity
				.csrf(csrfSpec -> csrfSpec.disable())
				.authorizeExchange(exchange ->
						exchange.pathMatchers("/eureka/**")
								.permitAll()
								.anyExchange()
								.authenticated()
				)
				.oauth2ResourceServer(oAuth2ResourceServerSpec -> {
					oAuth2ResourceServerSpec.jwt(jwtSpec -> jwtSpec.jwtDecoder(jwtDecoder()));
				});
		return serverHttpSecurity.build();
	}

	@Bean
	public ReactiveJwtDecoder jwtDecoder() {
		return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);}
}
