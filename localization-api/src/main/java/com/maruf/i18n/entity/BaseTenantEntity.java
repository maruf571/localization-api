package com.maruf.i18n.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author maruf
 */
@Setter
@Getter
@MappedSuperclass
public class BaseTenantEntity extends BaseEntity{

    @NotBlank
    @NotEmpty
    @Column(updatable = false, nullable = false)
    private String tenant;
}
