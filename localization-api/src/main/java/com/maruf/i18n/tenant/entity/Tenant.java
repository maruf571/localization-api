package com.maruf.i18n.tenant.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "TENANT")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    protected String id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String url;

}
