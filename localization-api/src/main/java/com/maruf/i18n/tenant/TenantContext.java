package com.maruf.i18n.tenant;

/**
 * Created by maruf on 7/21/17.
 */
public class TenantContext {

    private static final ThreadLocal<Long> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(Long tenant) {
        currentTenant.set(tenant);
    }

    public static Long getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
