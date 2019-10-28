package com.meetup.controller.jwtsecurity;

import static com.meetup.controller.jwtsecurity.JwtSecurityConstants.SECRET;
import static com.meetup.controller.jwtsecurity.JwtSecurityConstants.VALIDITY_IN_MS;

import com.meetup.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * . gets token from cookie and authenticates user
 */
@Component
@Slf4j
public class JwtTokenProvider {

    /**
     * . string that will be an encoded SECRET
     */
    private String secretKey;
    /**
     * . custom UserDetailsService
     */
    @Autowired
    private AuthenticationService userDetailsService;

    /**
     * . encode secret
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(SECRET
            .getBytes()); //userDetailsService   = new AuthenticationService();
    }

    /**
     * . creates new token
     *
     * @param username String
     * @param roles List<String>
     * @param userId user's ID in the database
     * @return string token
     */
    public String createToken(final String username, final List<String> roles,
        final Integer userId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        claims.put("userId", userId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_IN_MS);
        return Jwts.builder()//
            .setClaims(claims)//
            .setIssuedAt(now)//
            .setExpiration(validity)//
            .signWith(SignatureAlgorithm.HS256, secretKey)//
            .compact();
    }

    /**
     * . authenticate user by token
     *
     * @param token String
     * @return Authentication
     */
    public Authentication getAuthentication(final String token) {
log.debug(getUsername(token));
        UserDetails userDetails = this.userDetailsService
            .loadUserByUsername(getUsername(token));
        log.debug(
            "IN AUTH: " + userDetails.getUsername() + "\nroles: " + userDetails
                .getAuthorities().size());

        return new UsernamePasswordAuthenticationToken(userDetails, "",
            userDetails.getAuthorities());
    }

    /**
     * .
     *
     * @param token String
     * @return String
     */
    public String getUsername(final String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            .getBody().getSubject();
    }

    /**
     * Extracts user ID from token.
     *
     * @param token token
     * @return user's id
     */
    public Integer getUserId(final String token) {
        return (Integer) Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).getBody().get("userId");
    }

    /**
     * . method for getting token from Cookies
     *
     * @param req HttpRequest
     * @return String
     */
    public String resolveToken(final HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return null;
        }
        // TODO null in filter?
        return Arrays.stream(cookies)
            .filter(c -> c.getName().equals("token"))
            .findAny()
            .map(Cookie::getValue)
            .orElse(null);
    }

    /**
     * .
     *
     * @param token String
     * @return bool
     */
    boolean validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
