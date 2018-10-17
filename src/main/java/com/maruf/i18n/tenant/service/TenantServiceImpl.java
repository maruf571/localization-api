package com.maruf.i18n.tenant.service;

import com.maruf.i18n.tenant.entity.Tenant;
import com.maruf.i18n.tenant.repository.TenantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TenantServiceImpl implements TenantService {

    private TenantRepository tenantRepository;
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Tenant create(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant update(Tenant tenant) {
        tenantRepository
                .findById(tenant.getId())
                .orElseThrow(() -> new EntityNotFoundException("tenant not found"));

        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant findById(String projectId) {
        return tenantRepository
                .findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("tenant not found"));
    }

    @Override
    public Page<Tenant> findAll(Pageable pageable) {
        return tenantRepository.findAll(pageable);
    }

    @Override
    public void delete(String projectId) {
        tenantRepository.deleteById(projectId);
    }
}
