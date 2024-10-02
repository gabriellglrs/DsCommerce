package com.devsuperior.dscommerce.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;


@Getter
@NoArgsConstructor
@SuperBuilder
public class CustomError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}