package com.maruf.localization.tenant;

import com.maruf.localization.security.auth.jwt.extractor.TokenExtractor;
import com.maruf.localization.security.config.JwtSettings;
import com.maruf.localization.security.config.WebSecurityConfig;
import com.maruf.localization.security.model.token.RawAccessJwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maruf on 7/21/17.
 */
@Slf4j
@Component
public class TenantInterceptor  extends HandlerInterceptorAdapter {

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtSettings jwtSettings;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        boolean tenantSet = false;
        String tokenPayload = request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        String tenant = token.parseClaims(jwtSettings.getTokenSigningKey()).getBody().get("tenant").toString();
        log.debug("Current tenant {}", tenant);

        if(tenant != null) {
            TenantContext.setCurrentTenant(tenant);
            tenantSet = true;
            log.debug("Current user domain is " + tenant );
        }else {
            log.error("User domain is not found for the user ");
        }
        return tenantSet;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        TenantContext.clear();
    }

}
