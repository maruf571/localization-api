package com.maruf.localization.security.auth.jwt;

import com.maruf.localization.security.auth.JwtAuthenticationToken;
import com.maruf.localization.security.config.JwtSettings;
import com.maruf.localization.security.model.UserContext;
import com.maruf.localization.security.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtSettings jwtSettings;

    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        String tenant =  jwsClaims.getBody().get("tenant").toString();
        List<String> scopes = jwsClaims.getBody().get("roles", List.class);
        List<GrantedAuthority> authorities = scopes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // null if not acting on behalf of anyone
        //Map<String, Object> details = jwsClaims.getBody().get("act", Map.class);

        UserContext context = UserContext.create(subject, authorities, tenant);
        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
