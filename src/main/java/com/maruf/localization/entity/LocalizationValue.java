package com.maruf.localization.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
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
@Data
@Entity
@Table(name = "LOCALIZATION_VALUE")
public class LocalizationValue extends BaseEntity {

    @Column(columnDefinition = "TEXT", nullable = false)
    private String value;

    @ManyToOne
    private Language language;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Localization localization;
}
