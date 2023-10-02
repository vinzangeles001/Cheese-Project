/**
 * 
 */
package ca.sheridancollege.angelevi.assignment04.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ca.sheridancollege.angelevi.assignment04.services.UserDetailsServiceImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServ;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
//configures authorization stuff
//		http.csrf().disable().headers().frameOptions().disable();
//		http.authorizeRequests();
		
		
		//this is all the web pages and directories that the user or the 
		//manager's allowed to see. 
		http.authorizeRequests()
			.antMatchers("/inventory/**").hasRole("MANAGER")
			.antMatchers("/", "/view","/CSS/**", "/images/**", "/register").permitAll()
			.anyRequest().authenticated()
		.and()
		
		//when the user logs in it will go straight to the web page
		// were they came from
		.formLogin()
				.loginPage("/login").permitAll()
		.and()
		
		//this is when the user sign out the account it will make sure that 
		//the session and the authentication will be destroy and it will go 
		//straight to the main page
		.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{

		//this is connected to the user detailsServ, this will grab
		//infromation thru the database for the manager/customer accounts.
		auth.userDetailsService(userDetailsServ).passwordEncoder(passwordEncoder());

	}
}
