package com.tap.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWToken {
	
	 private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	 
	  private long getCurrentTimeMillis() {
	        return new DateTime().getMillis();
	    }

	    private Date generateCurrentDate() {
	        return new Date(getCurrentTimeMillis());
	    }
	
	 public String getUserNameFromToken(String Token) {
		 String username;
		 try {
			 final Claims claims = this.getClaimsFromToken(Token);
			 username = claims.getSubject();
		 }
		 catch (Exception e) {
	            username = null;
	        }
	        return username;
	 }
	 
	 public String generateToken(String username) {
	        String jws = Jwts.builder()
	                .setIssuer( "corpsms" )
	                .setSubject(username)
	                .setIssuedAt(generateCurrentDate())
	                .signWith( SIGNATURE_ALGORITHM, "coporate" )
	                .compact();
	        return jws;
	    }
	 
	  public Claims getClaimsFromToken(String token) {
	        Claims claims;
	        try {
	            claims = Jwts.parser()
	                    .setSigningKey("coporate")
	                    .parseClaimsJws(token)
	                    .getBody();
	        } catch (Exception e) {
	            claims = null;
	        }
	        return claims;
	    }
	  


}
