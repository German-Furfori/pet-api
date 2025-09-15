package com.mdotm.api.dto;

import lombok.Data;

@Data
public class PetResponseDto {
    private Long id;
    private String name;
    private String species;
    private Integer age;
    private String ownerName;
}