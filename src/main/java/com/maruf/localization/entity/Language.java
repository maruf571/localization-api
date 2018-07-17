package com.maruf.localization.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author maruf
 */
@Data
@Entity
@Table(name = "LANGUAGE")
public class Language extends BaseEntity {

    private String name;

    private String code;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isRtl;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
