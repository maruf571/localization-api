package com.maruf.i18n.project.entity;

import com.maruf.i18n.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String url;

}
