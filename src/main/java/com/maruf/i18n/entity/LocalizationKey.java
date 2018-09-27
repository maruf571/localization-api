package com.maruf.i18n.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

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

@Setter
@Getter
@Entity
@Table( name = "LOCALIZATION_KEY",
        uniqueConstraints = @UniqueConstraint(columnNames = {"langKey", "project_id"}),
        indexes = {@Index(name = "idx_localization", columnList = "project_id")})
public class LocalizationKey extends BaseEntity {

    @Column(nullable = false)
    private String langKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "localizationKey", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<LocalizationValue> localizationValues;

}
