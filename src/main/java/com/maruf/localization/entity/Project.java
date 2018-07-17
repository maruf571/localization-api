package com.maruf.localization.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity {

    private String name;

    private String url;

}
