package ru.local.projectmanager.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.local.projectmanager.entity.Role;
import ru.local.projectmanager.utils.Constants;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private Long expiredInMilliseconds;

    private Key secretKey;

    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String createToken(String username, List<Role> roles) {
        var claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(roles));
        var currentDate = new Date();
        var validity = new Date(currentDate.getTime() + expiredInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return request.getHeader(Constants.AUTHORIZATION.getConstValue());
    }

    public Authentication getAuthentication(String token) {
        var userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        var claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private List<String> getRoleNames(List<Role> roles) {
        var roleNames = new ArrayList<String>();
        roles.forEach(role -> roleNames.add(role.getRoleName()));
        return roleNames;
    }
}
