package com.maruf.i18n.project.entity;

import com.maruf.i18n.entity.BaseEntity;
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
        uniqueConstraints = @UniqueConstraint(columnNames = {"tenant", "name"}))
public class Project extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

}
