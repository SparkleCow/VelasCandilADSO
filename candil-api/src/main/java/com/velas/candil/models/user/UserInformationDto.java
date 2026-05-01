package com.velas.candil.models.user;

import java.util.Set;

public record UserInformationDto(
        Long id,
        String username,
        String fullName,
        String imageUrl,
        Set<String> roles
) {
}
