package com.maruf.i18n.tenant.repository;

import com.maruf.i18n.tenant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
