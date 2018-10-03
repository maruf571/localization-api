package com.maruf.i18n.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author maruf
 */
@Data
@MappedSuperclass
public class BaseTenantEntity extends BaseEntity{

    @Column(updatable = false)
    private String tenant;
}
