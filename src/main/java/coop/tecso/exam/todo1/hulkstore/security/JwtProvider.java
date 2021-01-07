package coop.tecso.exam.todo1.hulkstore.security;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service

public class JwtProvider {
	
	private static final String SECRET_KEY = "BxBwm9gsjm4qeo1mYiGvESxjpmHHq0UBIcb2Uy508jD5GAENaloaJq2KhLmF2pZ";
	
	
	public String generateJwt(UserDto user) {

		return Jwts.builder()
					.setId(UUID.randomUUID().toString())
					.claim("user", user)
				    .setIssuedAt(new Date(System.currentTimeMillis()))
				    .setExpiration(new Date(System.currentTimeMillis() + 600000))
				    .signWith(getSigningKey())
				    .compact();
		
	}
	
	
	private static Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	
}
