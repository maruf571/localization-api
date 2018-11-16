package com.maruf.i18n.entity;

import com.maruf.i18n.entity.BaseTenantEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Setter
@Getter
@Entity
@Table(name = "PROJECT",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant", "code"}))
public class Project extends BaseTenantEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(nullable = false)
    private String code;

    @Column(name = "url")
    private String url;

}
