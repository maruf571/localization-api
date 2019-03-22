package com.maruf.localization.tenant;

/**
 * Created by maruf on 7/21/17.
 */
public class TenantContext {

    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
