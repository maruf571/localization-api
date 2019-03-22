package com.maruf.localization.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author maruf
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length = 32)
    protected String id;


}
