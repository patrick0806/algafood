package com.nicezi.patrick.algafood.domain.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionData {
    private LocalDateTime time;
    private String message;
}
