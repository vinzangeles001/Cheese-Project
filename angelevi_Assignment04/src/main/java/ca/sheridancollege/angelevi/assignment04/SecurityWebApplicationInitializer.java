package ca.sheridancollege.angelevi.assignment04;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import ca.sheridancollege.angelevi.assignment04.security.SecurityConfig;



public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		
		super(SecurityConfig.class);
	}



}
