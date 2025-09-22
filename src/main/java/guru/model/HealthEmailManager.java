package guru.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class HealthEmailManager {
	@Autowired
	JavaMailSender JMS;
	SimpleMailMessage mailMessage=new SimpleMailMessage();
	public String tOMail(String toMail,String subject,String message) {
		try {
		mailMessage.setFrom("gurushaileshmopuri@gmail.com");
		mailMessage.setTo(toMail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		JMS.send(mailMessage);
		return "200::password sent to registered mail id";
		}catch(Exception e) {
			return "401::"+e.getMessage();
			
		}
	}
		

}
