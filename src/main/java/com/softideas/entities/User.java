package com.softideas.entities;

import org.hibernate.envers.Audited;
import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Audited
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_CODE")
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
