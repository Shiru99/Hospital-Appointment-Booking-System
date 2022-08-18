package get.sterlite.Authentication.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

    private String SECRET_KEY = "get.sterlite.shiru99";

    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Date extractExpiration(String token) throws MalformedJwtException {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws MalformedJwtException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws MalformedJwtException {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT token");
        }
    }

    private Boolean isTokenExpired(String token) throws MalformedJwtException {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String mobileNum) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, mobileNum);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10Hrs
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String JWTToken) throws MalformedJwtException {
        return !isTokenExpired(JWTToken);
    }
}
