package com.velas.candil.config.initializer;

import com.velas.candil.entities.candle.Candle;
import com.velas.candil.entities.user.Role;
import com.velas.candil.models.candle.CategoryEnum;
import com.velas.candil.models.candle.FeatureEnum;
import com.velas.candil.models.candle.MaterialEnum;
import com.velas.candil.models.user.RoleEnum;
import com.velas.candil.repositories.CandleRepository;
import com.velas.candil.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final CandleRepository candleRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            createRoleIfNotExists(List.of(RoleEnum.USER, RoleEnum.ADMIN));
            createCandlesIfNotExists();
        };
    }

    private void createRoleIfNotExists(List<RoleEnum> roleEnums) {
        roleEnums.forEach(role ->
                roleRepository.findByRole(role)
                        .orElseGet(() ->
                                roleRepository.save(
                                        Role.builder()
                                                .role(role)
                                                .build()
                                )
                        )
        );
    }

    private void createCandlesIfNotExists() {

        if (candleRepository.count() > 0) return;

        Candle lavanda = Candle.builder()
                .name("Lavender Calm")
                .description("A relaxing lavender scented candle.")
                .principalImage("lavender-main.jpg")
                .stock(50)
                .price(35_000.0)
                .materialEnums(Set.of(MaterialEnum.SOY_WAX))
                .featureEnums(Set.of(FeatureEnum.SCENTED))
                .categories(Set.of(CategoryEnum.AROMATIC))
                .images(List.of("lavender-1.jpg", "lavender-2.jpg"))
                .build();

        Candle vainilla = Candle.builder()
                .name("Vanilla Dream")
                .description("Warm vanilla scent for cozy spaces.")
                .principalImage("vanilla-main.jpg")
                .stock(40)
                .price(32_000.0)
                .materialEnums(Set.of(MaterialEnum.BEESWAX))
                .featureEnums(Set.of(FeatureEnum.SCENTED))
                .categories(Set.of(CategoryEnum.DECORATIVE))
                .images(List.of("vanilla-1.jpg", "vanilla-2.jpg"))
                .build();

        Candle sandalwood = Candle.builder()
                .name("Sandalwood Ritual")
                .description("Deep woody aroma for meditation and grounding.")
                .principalImage("sandalwood-main.jpg")
                .stock(30)
                .price(38_000.0)
                .materialEnums(Set.of(MaterialEnum.SOY_WAX))
                .featureEnums(Set.of(FeatureEnum.SCENTED))
                .categories(Set.of(CategoryEnum.AROMATIC))
                .images(List.of("sandalwood-1.jpg", "sandalwood-2.jpg"))
                .build();

        Candle citrus = Candle.builder()
                .name("Citrus Energy")
                .description("Fresh citrus blend to energize your mornings.")
                .principalImage("citrus-main.jpg")
                .stock(45)
                .price(30_000.0)
                .materialEnums(Set.of(MaterialEnum.SOY_WAX))
                .featureEnums(Set.of(FeatureEnum.SCENTED))
                .categories(Set.of(CategoryEnum.DECORATIVE))
                .images(List.of("citrus-1.jpg", "citrus-2.jpg"))
                .build();

        Candle ocean = Candle.builder()
                .name("Ocean Breeze")
                .description("Clean marine scent inspired by coastal winds.")
                .principalImage("ocean-main.jpg")
                .stock(35)
                .price(34_000.0)
                .materialEnums(Set.of(MaterialEnum.BEESWAX))
                .featureEnums(Set.of(FeatureEnum.SCENTED))
                .categories(Set.of(CategoryEnum.ROMANTIC))
                .images(List.of("ocean-1.jpg", "ocean-2.jpg"))
                .build();

        Candle cinnamon = Candle.builder()
                .name("Cinnamon Spice")
                .description("Warm spicy aroma perfect for cozy evenings.")
                .principalImage("cinnamon-main.jpg")
                .stock(25)
                .price(36_000.0)
                .materialEnums(Set.of(MaterialEnum.SOY_WAX))
                .featureEnums(Set.of(FeatureEnum.HANDMADE))
                .categories(Set.of(CategoryEnum.DECORATIVE))
                .images(List.of("cinnamon-1.jpg", "cinnamon-2.jpg"))
                .build();

        Candle rose = Candle.builder()
                .name("Rose Harmony")
                .description("Soft floral scent for romantic atmospheres.")
                .principalImage("rose-main.jpg")
                .stock(28)
                .price(37_000.0)
                .materialEnums(Set.of(MaterialEnum.BEESWAX))
                .featureEnums(Set.of(FeatureEnum.HANDMADE))
                .categories(Set.of(CategoryEnum.RELIGIOUS))
                .images(List.of("rose-1.jpg", "rose-2.jpg"))
                .build();

        Candle coffee = Candle.builder()
                .name("Coffee House")
                .description("Rich roasted coffee scent for focus and comfort.")
                .principalImage("coffee-main.jpg")
                .stock(50)
                .price(33_000.0)
                .materialEnums(Set.of(MaterialEnum.SOY_WAX))
                .featureEnums(Set.of(FeatureEnum.SCENTED))
                .categories(Set.of(CategoryEnum.DECORATIVE))
                .images(List.of("coffee-1.jpg", "coffee-2.jpg"))
                .build();

        candleRepository.saveAll(List.of(lavanda, vainilla,lavanda, vainilla,
                sandalwood, citrus, ocean, cinnamon, rose, coffee));
    }
}