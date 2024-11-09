package com.project.base.proyectobase.infrastructure.security;

import com.project.base.proyectobase.domain.auth.User;
import com.project.base.proyectobase.domain.auth.gateway.JwtGateway;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements JwtGateway {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public String generarToken(User user, Map<String, Object> extraClaims) {

        Date fechaCreacionToken = new Date(System.currentTimeMillis());
        Date fechaExpirationToken = new Date((EXPIRATION_IN_MINUTES * 60 * 1000)+ fechaCreacionToken.getTime());

        String jwt = Jwts.builder()
                .claims(extraClaims)//los claims son las propiedades del payload del JWT.
                .subject(user.getUsername())
                .issuedAt(fechaCreacionToken).expiration(fechaExpirationToken)//fecha de expiración del token.
                .header()
                .type("JWT").and()
                .signWith(generateKey(), Jwts.SIG.HS256)//firmar
                .compact();
        return jwt;
    }

    private SecretKey generateKey() {
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);//decodificando un BASE64
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    public String getUserName(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = getUserName(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token,Claims::getExpiration);
    }

}
