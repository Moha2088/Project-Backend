package org.example.projectbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.projectbackend.models.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {
    
    @Value("${jwt.key}")
    private String key; 
    
    public String extractUserName(String token) {
        return getClaim(token, Claims::getSubject);
    }
    
//    public String generateToken(UserDetails details) {
//        return generateToken(new HashMap<>(), details);
//    }
    
    public String generateToken(UserDetails details) {
        var claims = new HashMap<String, Object>();
        User user = (User) details;
        claims.put("organisation_id", user.getOrganisation() == null ? "null" : user.getOrganisation().getId());
        claims.put("role", user.getRole());
        
        return Jwts.builder()
                .claims(claims)
                .subject(details.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }
    
    public boolean isTokenValid(String token, UserDetails details) {
        final String userName = extractUserName(token);
        return userName.equals(details.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) { 
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getClaims(token);
        return claimResolver.apply(claims);
    }
    
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(bytes);
    }
}
