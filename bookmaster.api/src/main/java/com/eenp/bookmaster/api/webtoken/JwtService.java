package com.eenp.bookmaster.api.webtoken;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "C40112FEBE19BE80A60192D3995B92CCEBDEE6BEED6895C2C6ACA76703B2F5D0556DD998A48DAD26F5F1B25776A3EFA12E238E99FEDCE2EF56F93D695BCE2E2E";
	private static final long VALIDITY = TimeUnit.MINUTES.toMillis(60);
	
	public String generateToken(UserDetails userDetails) {
		Map<String,String> claims = new HashMap<>();
		claims.put("iss","bookmaster.api");
		return Jwts.builder()
			.claims(claims)
			.subject(userDetails.getUsername())
			.issuedAt(Date.from(Instant.now()))
			.expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
			.signWith(generateKey())
			.compact();
	}
	
	private SecretKey generateKey() {
		byte[] decodedKey = Base64.getDecoder().decode(SECRET);
		return Keys.hmacShaKeyFor(decodedKey);
	}
	
	public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                 .verifyWith(generateKey())
                 .build()
                 .parseSignedClaims(jwt)
                 .getPayload();
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

}
