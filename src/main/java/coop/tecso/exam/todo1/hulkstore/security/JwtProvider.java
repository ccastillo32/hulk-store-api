package coop.tecso.exam.todo1.hulkstore.security;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service

public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secretKey;
	
	
	public String generateJwt(UserDto user) {
		
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		Key signingKey = Keys.hmacShaKeyFor(keyBytes);

		return Jwts.builder()
					.setId(UUID.randomUUID().toString())
					.claim("user", user)
				    .setIssuedAt(new Date(System.currentTimeMillis()))
				    .setExpiration(new Date(System.currentTimeMillis() + 600000))
				    .signWith(signingKey)
				    .compact();
		
	}

	
}
