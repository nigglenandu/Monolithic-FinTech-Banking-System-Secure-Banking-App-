package com.NiggleNandu.FinTech.Banking.System.Secure.Banking.App.JwtSecurity.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")  // table name changed to avoid reserved keyword conflicts
public class RoleEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING) // store as a string instead of an integer
    @Column(unique = true, nullable = false)
    private Role role;
}
