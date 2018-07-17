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

@Data
@Entity
@Table( name = "LOCALIZATION",
        uniqueConstraints = @UniqueConstraint(columnNames = {"langKey", "project_id"}),
        indexes = {@Index(name = "idx_localization", columnList = "project_id")})
public class Localization extends BaseEntity {

    @Column(nullable = false)
    private String langKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "localization", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LocalizationValue> localizationValues;

}
