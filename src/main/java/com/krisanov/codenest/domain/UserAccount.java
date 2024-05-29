package com.krisanov.codenest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * UserAccount is an entity that represents a user in the system.
 * It implements UserDetails interface which is a core interface in Spring Security framework.
 * It includes user details like username, email, password, roles etc.
 *
 * @see UserDetails
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_accounts")
public class UserAccount implements UserDetails {

    /**
     * Unique ID for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Username of the user.
     */
    @NotBlank(message = "Username is required")
    @Size(max = 20, message = "Username must be less than 20 characters")
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Email of the user.
     */
    @NotBlank(message = "Email is required")
    @Email
    @Size(max = 50, message = "Email must be less than 50 characters")
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * First name of the user.
     */
    @NotBlank(message = "First name is required")
    @Size(max = 20, message = "First name must be less than 20 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Last name of the user.
     */
    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name must be less than 20 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Password of the user.
     */
    @ToString.Exclude
    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    /**
     * Roles assigned to the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_accounts_roles",
            joinColumns = @JoinColumn(name = "user_account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @Builder.Default
    private Set<Role> authorities = new HashSet<>();

    /**
     * Checks if the account has not expired.
     *
     * @return a boolean indicating if the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the account is not locked.
     *
     * @return a boolean indicating if the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the credentials are not expired.
     *
     * @return a boolean indicating if the credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the account is enabled.
     *
     * @return a boolean indicating if the account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
