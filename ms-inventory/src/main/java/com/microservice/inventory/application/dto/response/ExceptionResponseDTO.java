package com.microservice.inventory.application.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponseDTO {
    private String code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
