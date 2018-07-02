package com.maruf.localization.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author maruf
 */
@Data
@Entity
@Table(name = "LANGUAGE")
public class Language extends BaseEntity {

    private String langKey;

    private String langCode;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isRtl;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isActive;
}
