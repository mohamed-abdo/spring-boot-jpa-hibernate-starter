package com.softideas.entities;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.io.Serializable;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "ROLE")
@Audited(targetAuditMode = NOT_AUDITED)
public class Role implements Serializable{
    @Id
    @Column(name = "CODE")
    private RoleCode code;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    public RoleCode getCode() {
        return code;
    }

    public void setCode(RoleCode code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
