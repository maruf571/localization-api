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
@Table(
        name = "LOCALIZATION",
        uniqueConstraints = @UniqueConstraint(columnNames = {"langKey", "language_id"}),
        indexes = @Index(name = "idx_localization", columnList = "language_id")
)
public class Localization extends BaseEntity {

    @Column(nullable = false)
    private String langKey;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String value;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Language language;

}
