package com.velas.candil.entities;

import com.velas.candil.models.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

/**
 * The Role entity implements GrantedAuthority to integrate with Spring Security.
 * By implementing GrantedAuthority, each Role represents an authority granted
 * to a user, such as a permission or role name
 * Spring Security uses this authority information during authorization to
 * determine whether an authenticated user has access to specific resources.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public @Nullable String getAuthority() {
        return role.name();
    }
}
