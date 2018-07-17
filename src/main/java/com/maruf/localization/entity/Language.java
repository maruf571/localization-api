package com.maruf.localization.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

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

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
