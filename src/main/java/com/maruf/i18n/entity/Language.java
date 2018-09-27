package com.maruf.i18n.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author maruf
 */
@Data
@Entity
@Table(name = "LANGUAGE",
        indexes = {@Index(name = "idx_language", columnList = "project_id")})
public class Language extends BaseEntity {

    private String name;

    private String code;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isRtl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isActive;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
