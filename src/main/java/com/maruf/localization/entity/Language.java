package com.maruf.localization.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "LANGUAGE")
public class Language extends BaseEntity {

    private String name;

    @Column(unique = true)
    private String code;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isRtl;

    @Column(columnDefinition = "TINYINT", length = 1)
    private Boolean isActive;

}
