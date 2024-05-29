package com.krisanov.codenest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Role is an entity that represents a role in the system.
 * It implements GrantedAuthority interface which is a core interface in Spring Security framework.
 *
 * @see GrantedAuthority
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    /**
     * Unique ID for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Name of the role.
     */
    @NotBlank(message = "Role name is required")
    private String authority;
}
