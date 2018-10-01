package com.maruf.i18n.security.model;

import com.maruf.i18n.security.entity.User;
import lombok.Getter;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class UserContext{

    private final String username;

    private final List<GrantedAuthority> authorities;

    private final Long tenant;

    private UserContext(String username, List<GrantedAuthority> authorities, Long tenant) {
        this.username = username;
        this.authorities = authorities;
        this.tenant = tenant;
    }
    
    public static UserContext create(String username, List<GrantedAuthority> authorities, Long tenant) {
        if (StringUtils.isEmpty(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authorities, tenant);
    }

    public static UserContext create(User user) {
        if (user == null || StringUtils.isEmpty(user.getEmail())) {
            throw new IllegalArgumentException("Username is empty");
        }

        if (user.getRoles() == null) {
            throw new InsufficientAuthenticationException("User has no roles assigned");
        }

        List<GrantedAuthority> roles = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());

        return new UserContext(user.getEmail(), roles, user.getTenant());
    }

}
