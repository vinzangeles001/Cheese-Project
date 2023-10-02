package ca.sheridancollege.angelevi.assignment04.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.angelevi.assignment04.database.DatabaseAccess;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired @Lazy
	private DatabaseAccess da;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		ca.sheridancollege.angelevi.assignment04.beans.User user = da.findUser(userName);
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + userName
					+ " not found in database.");
		}
		
		List<String> roles = da.getRolesByUserId(user.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if(roles != null) {
			for(String role : roles) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		UserDetails userDetails = new User(user.getUserName(), user.getPassword(), grantList);
		return userDetails;
		
	}

}
