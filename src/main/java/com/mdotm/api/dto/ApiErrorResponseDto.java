package com.mdotm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorResponseDto {
    private List<ErrorResponseDto> errors;
}
