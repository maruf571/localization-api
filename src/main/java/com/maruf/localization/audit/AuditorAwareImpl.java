package com.maruf.localization.audit;

import com.maruf.localization.security.model.UserContext;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * This is a Audit Java class that provides a way to get logged in user information.
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == "anonymousUser") {
            return Optional.empty();
        }
        return Optional.of(((UserContext) authentication.getPrincipal()).getUsername());
    }
}
