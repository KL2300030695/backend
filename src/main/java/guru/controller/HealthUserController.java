
package guru.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.model.HealthUser;
import guru.model.HealthUserManager;


@RestController
@RequestMapping("/healthuser")
@CrossOrigin(origins = "*")
public class HealthUserController {

    /*private final HealthUserManager UM;

     // Ensure Spring injects HealthUserManager
    public HealthUserController(HealthUserManager UM) {
        this.UM = UM;
    }*/
    @Autowired
    HealthUserManager UM;

    @PostMapping("/signup")
    public String signup(@RequestBody HealthUser U) {
        return UM.addUSer(U);
    }
    
    @GetMapping("/forgetpassword/{email}")
	public String forgetpassword(@PathVariable("email") String emailid) 
	{
		return UM.changePassword(emailid);	
	}
    @PostMapping("/signin")
	public String signin(@RequestBody HealthUser U) 
	{
		return UM.CheckCredentials(U.getEmail(), U.getPassword());
		
	}
    
    @PostMapping("/getname")
	public String getname(@RequestBody Map<String,String> data) {
		return UM.getFullname(data.get("crsid"));
	}
}
