package com.bit.lotte.fresh.cart.common.application.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {
    private final String code;
    private final HttpStatus status;
    private final String message;
}
