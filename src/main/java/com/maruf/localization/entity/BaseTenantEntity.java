package com.maruf.localization.entity;

import com.maruf.localization.tenant.TenantContext;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author maruf
 */
@Setter
@Getter
@FilterDef(name = "tenantFilter", parameters = @ParamDef(name = "tenant", type = "string"))
@Filter( name = "tenantFilter", condition = "tenant = :tenant ")
@MappedSuperclass
public class BaseTenantEntity extends BaseEntity{

    @NotBlank
    @NotEmpty
    @Column(updatable = false, nullable = false)
    private String tenant;


    @PrePersist
    public void onPrePersist() {

        if(TenantContext.getCurrentTenant() != null && !TenantContext.getCurrentTenant().isEmpty()) {
            this.tenant = TenantContext.getCurrentTenant();
        }
    }
}
