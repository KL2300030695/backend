package guru.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class TokenManager {
	public final String SEC_KEY="9989087633wijfnwirjenjehfniuefdejfb";
	public final SecretKey key=Keys.hmacShaKeyFor(SEC_KEY.getBytes());
	public String createToken(String email) {
		Map<String, String> data=new HashMap<>();
		data.put("email", email);
		return Jwts.builder()
				.setClaims(data)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
				.signWith(key)
				.compact();
	}
	public String checkToken(String jwttoken) {
		Claims c=Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwttoken)
				.getBody();
		Date expiry=c.getExpiration();
		if (expiry==null||expiry.before(new Date()))
		return "401";
		return c.get("email", String.class);
			
	}

}
