package com.maruf.localization.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "LOCALIZATION")
public class Localization extends BaseEntity {

    @Column(nullable = false)
    private String langKey;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @JsonManagedReference
    @OneToMany(mappedBy = "localization", fetch = FetchType.LAZY)
    private List<LocalizationValue> localizationValues;

}
