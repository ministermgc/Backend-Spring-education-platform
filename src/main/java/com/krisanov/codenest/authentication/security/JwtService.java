package com.krisanov.codenest.authentication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * This Service class manages all JWT (JSON Web Token) related operations.
 * It provides functionalities such as token generation, extraction of details from the token,
 * and validity checks on the tokens.
 */
@Service
public class JwtService {

    /**
     * Secret Key string for token generation.
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /**
     * Algorithm used for token generation.
     */
    private final MacAlgorithm ALGORITHM = SIG.HS256;

    /**
     * Generates an access token for the given user details.
     *
     * @param userDetails the user details
     * @return the generated access token
     */
    public String generateAccessToken(UserDetails userDetails) {
        checkUserDetails(userDetails);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray());
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expirationAt = new Date(System.currentTimeMillis() + 1000 * 60 * 10); // 10 minutes
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims)
                .issuedAt(issuedAt)
                .expiration(expirationAt)
                .id(UUID.randomUUID().toString())
                .signWith(getSigningKey(), ALGORITHM)
                .compact();
    }

    /**
     * Generates a refresh token for the given user details.
     *
     * @param userDetails the user details
     * @return the generated refresh token
     */
    public String generateRefreshToken(UserDetails userDetails) {
        checkUserDetails(userDetails);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSigningKey(), ALGORITHM)
                .compact();
    }

    /**
     * Validates the token against the given user details.
     *
     * @param token the JWT token
     * @param userDetails the user details
     * @return true if the token is valid, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Extracts all claims from the given token.
     *
     * @param token the JWT token
     * @return the claims
     * @throws IllegalArgumentException if the token is null or empty
     */
    public Claims extractAllClaims(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts the username from the given token.
     *
     * @param token the JWT token
     * @return the username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the given token.
     *
     * @param token the JWT token
     * @return the expiration date
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if the given token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Checks the validity of the user details.
     *
     * @param userDetails the user details
     * @throws IllegalArgumentException if the user details or username is null
     */
    private void checkUserDetails(UserDetails userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("UserDetails can't be null");
        }

        if (userDetails.getUsername() == null) {
            throw new IllegalArgumentException("UserDetails.username can't be null");
        }
    }

    /**
     * Extracts a specific claim from the given token using the provided claims resolver.
     *
     * @param token the JWT token
     * @param claimsResolver the function to resolve the claim
     * @param <T> the type of the claim
     * @return the extracted claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieves the signing key used for token generation.
     *
     * @return the signing key
     */
    private SecretKey getSigningKey() {
        byte[] keyAsBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyAsBytes);
    }
}
