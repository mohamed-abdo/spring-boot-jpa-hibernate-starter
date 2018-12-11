package com.softideas.auditor.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="NAME")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
