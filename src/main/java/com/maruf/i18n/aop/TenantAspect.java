package com.maruf.i18n.aop;

import com.maruf.i18n.tenant.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    protected void ownerAwareRepositoryMethod(){ }

    @Around(value = "ownerAwareRepositoryMethod()")
    public Object enableOwnerFilter(ProceedingJoinPoint joinPoint) throws Throwable{

        if(TenantContext.getCurrentTenant() == null){
            return joinPoint.proceed();
        }

        log.debug("initializing tenant filter");
        Session session = null;
        try {
            session = entityManager.unwrap(Session.class);
            Filter filter = session.enableFilter("tenantFilter");
            filter.setParameter("tenant", TenantContext.getCurrentTenant());
        } catch (Exception ex) {
            log.error("Error enabling ownerFilter : Reason -" +ex.getMessage());
        }
        return joinPoint.proceed();

    }

}
