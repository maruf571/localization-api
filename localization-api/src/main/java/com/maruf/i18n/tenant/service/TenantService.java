package com.maruf.i18n.tenant.service;

import com.maruf.i18n.tenant.entity.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TenantService {

    Tenant create(Tenant tenant);

    Tenant update(Tenant tenant);

    Tenant findById(String projectId);

    Page<Tenant> findAll(Pageable pageable);

    void delete(String projectId);
}
