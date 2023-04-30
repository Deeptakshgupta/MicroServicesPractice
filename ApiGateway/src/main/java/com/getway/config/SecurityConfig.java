package com.getway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


/**Web Flux Security as WEbFlux for Providing Netty Environment and Security For Security providing both are being used
 * Only @EnableSecutiy Won't work**/

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	/** For normal Traditional Servlet container we would have used simple HttpSecurity
	 * Here
	 * we are using ServerHttpSecurity as we are using web Flux here 
	 * **/
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) 
	{
		//every exchange must be authenticated and use OATH Login for Authentication and then server configuration
		httpSecurity
		.authorizeExchange()
		.anyExchange()
		.authenticated()
		.and()
		.oauth2Client()
		.and()
		.oauth2ResourceServer()
		.jwt();
		
		return httpSecurity.build();
		
	}
	
}
