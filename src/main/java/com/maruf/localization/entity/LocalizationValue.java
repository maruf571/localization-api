package com.maruf.localization.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author maruf
 */
@Setter
@Getter
@Entity
@Table( name = "LOCALIZATION_VALUE",
        uniqueConstraints = @UniqueConstraint(columnNames = {"localizationKey_id", "language_id"}),
        indexes = {@Index(name = "idx_localization_value", columnList = "language_id")})
public class LocalizationValue extends BaseTenantEntity {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String value;

    @ManyToOne
    private Language language;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private LocalizationKey localizationKey;
}
