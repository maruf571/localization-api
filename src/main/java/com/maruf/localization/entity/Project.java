package com.maruf.localization.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String url;

}
