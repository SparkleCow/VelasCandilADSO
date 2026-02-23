package com.velas.candil.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity that represents an account activation token.
 * This token is generated when a user registers and is used
 * to verify and activate their account.
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime validatedAt;
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}