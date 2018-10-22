package com.fpt.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {
	@Value("${security.jwt.expiration-time-short}")
	private Long jwtExpirationTimeShort;

	@Value("${security.jwt.expiration-time-long}")
	private Long jwtExpirationTimeLong;

	@Value("${security.jwt.secret}")
	private String jwtSecret;

	public String generateToken(Long userId, Boolean isLongToken) throws Exception {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationTimeShort);
		if (isLongToken == Boolean.TRUE) {
			expiryDate = new Date(now.getTime() + jwtExpirationTimeLong);
		}
		return Jwts.builder().setSubject(userId.toString()).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public Long getInfoFromJWT(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException
				| IllegalArgumentException ex) {
		}
		return false;
	}
}
