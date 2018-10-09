package com.maruf.i18n.aop;

import com.maruf.i18n.tenant.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
@Aspect
@Component
public class TenantAspect {

    @PersistenceContext
    private EntityManager entityManager;

    @Around("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public Object enableOwnerFilter(ProceedingJoinPoint joinPoint) throws Throwable{

        if(TenantContext.getCurrentTenant() == null || TenantContext.getCurrentTenant().isEmpty()){
            return joinPoint.proceed();
        }

        Session session = null;
        try {
            session = entityManager.unwrap(Session.class);
            Filter filter = session.enableFilter("tenantFilter");
            filter.setParameter("tenant", TenantContext.getCurrentTenant());
        } catch (Exception ex) {
            log.error("Error enabling ownerFilter : Reason -" +ex.getMessage());
        }

        return joinPoint.proceed();

        /*Object obj  = joinPoint.proceed();
        if ( session != null ) {
            session.disableFilter("tenantFilter");
        }*/

    }
}
