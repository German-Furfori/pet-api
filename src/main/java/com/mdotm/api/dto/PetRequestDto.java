package com.mdotm.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PetRequestDto {
    @NotBlank(message = "The name field is null or empty")
    private String name;
    @NotBlank(message = "The species field is null or empty")
    private String species;
    @PositiveOrZero(message = "The age field must be greater than or equal to 0")
    private Integer age;
    private String ownerName;
}