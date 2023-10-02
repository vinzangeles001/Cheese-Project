package ca.sheridancollege.angelevi.assignment04.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//this user's unique ID or the primary key value
	private long userId;
	
	//this is the user's username, cannot be null
	@NonNull 
	private String userName;
	
	//this is the email for user
	private String email;
	
	//this is the password of the user, this cannot be null
	@NonNull 
	private String password;
	
	private boolean enabled;
	
	
}
