package com.maruf.i18n.security.jwt;


import java.util.*;

public class JwtAuthorizationSettings {

    private Map<String, Set<String>> urlAccessAllowedRoleMap = new HashMap<>();

    public void add(String pattern, String role) {
        addRoles(pattern, role);
    }


    void add(String pattern, String role1, String role2) {
        addRoles(pattern, role1);
        addRoles(pattern, role2);
    }

    void add(String pattern, String... roles) {
        addRoles(pattern, roles);
    }

    public Set<String> getRoles(String pattern) {
        return urlAccessAllowedRoleMap.get(pattern);
    }

    public Set<String> getPatterns() {
        return urlAccessAllowedRoleMap.keySet();
    }

    private void addRoles(String pattern, String... roles) {

        Set<String> roleList = urlAccessAllowedRoleMap.get(pattern);

        if (roleList == null) {
            roleList = new HashSet<>();
        }

        if (roles != null && roles.length > 0) {
            roleList.addAll(Arrays.asList(roles));
        }

        urlAccessAllowedRoleMap.put(pattern, roleList);

    }

}
