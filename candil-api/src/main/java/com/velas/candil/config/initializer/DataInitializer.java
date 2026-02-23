package com.velas.candil.config.initializer;

import com.velas.candil.entities.user.Role;
import com.velas.candil.models.RoleEnum;
import com.velas.candil.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;

    @Bean
    CommandLineRunner initRoles() {
        return args -> createRoleIfNotExists(List.of(RoleEnum.USER, RoleEnum.ADMIN));
    }

    private void createRoleIfNotExists(List<RoleEnum> roleEnum) {

        roleEnum.forEach(role -> {
            roleRepository.findByRole(role)
                    .orElseGet(() -> roleRepository.save(
                            Role.builder()
                                    .role(role)
                                    .build()
                    ));
        });
    }
}