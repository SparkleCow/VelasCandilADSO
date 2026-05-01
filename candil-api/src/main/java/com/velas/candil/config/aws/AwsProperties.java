package com.velas.candil.config.aws;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * This class allows to get data from aws properties and use it as a bean.
 * Lombok's @Data annotation generates getters, setters, toString, equals, and hashCode methods.*/
@Configuration
@ConfigurationProperties(prefix = "aws.s3")
@Validated
@Data
public class AwsProperties {
    @NotNull
    private String key;
    @NotNull
    private String secret;
    @NotNull
    private String region;
    @NotNull
    private String bucket;
}