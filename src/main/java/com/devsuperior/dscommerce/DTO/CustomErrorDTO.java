package com.devsuperior.dscommerce.DTO;

import java.time.Instant;

public record CustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
}
