package com.maruf.i18n.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
/*import org.springframework.entity.core.Authentication;
import org.springframework.entity.core.context.SecurityContextHolder;
import org.un.oict.sfm.entity.model.UserContext;*/

/**
 * This is a Audit Java class that provides a way to get logged in user information.
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == "anonymousUser") {
            return null;
        }

        return ((UserContext) authentication.getPrincipal()).getUsername();*/

        return Optional.of("admin");
    }
}
