package guru.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.repository.HealthUsersRepository;






@Service
public class HealthUserManager {
	
	@Autowired
	HealthUsersRepository UR;
	@Autowired
	HealthEmailManager EM;
	@Autowired
	TokenManager jwt;
	public String addUSer(HealthUser U) 
	{
		if(UR.validateEmail(U.getEmail())>0)
			return "401::email id exist";
		
		UR.save(U);
		return "200::Registration done successfully";
	}
	
	public String changePassword(String email) {
		HealthUser U=UR.findById(email).get();
		 String message=String.format("dear %s,\n\n\n pass word is: %s",U.getFullname(),U.getPassword());
		return EM.tOMail(U.getEmail(),"Health Appointment system recover password", message);
	}
	public String CheckCredentials(String email,String password) 
	{
		if(UR.CheckCredentials(email, password)>0) 
		{
			String token=jwt.createToken(email);
			return "200::"+token;
		}
		return "401:: invalid credentials";
	}
	
	public String getFullname(String token) {
		String email=jwt.checkToken(token);
		if(email.equals("401")) 
		{
			return "401::Token expired";
		}else 
		{
			HealthUser u=UR.findById(email).get();
			return u.getFullname();
		}
		
	}
	


}
