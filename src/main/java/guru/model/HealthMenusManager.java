package guru.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import guru.repository.HealthMenusRepository;
import guru.repository.HealthUsersRepository;




@Service
public class HealthMenusManager {
	@Autowired
	HealthMenusRepository MR;
	@Autowired
	TokenManager jwt;
	@Autowired
	HealthUsersRepository UR;
	public String getMenus() 
	{
		List<String> ml=new ArrayList<String>();
		for(HealthMenus m:MR.findAll()) 
		{
			ml.add(new GsonBuilder().create().toJson(m));	
		}
		return ml.toString();
	}
	public String getMenusByRole(String token) {
		String email=jwt.checkToken(token);
		if(email.equals("401")) {
			return "401::token expired";
		}else {
			HealthUser U=UR.findById(email).get();
			List<HealthMenus> ml=MR.findByrole(U.getRole());
			return new GsonBuilder().create().toJson(ml).toString();
			
		}
	}

}
