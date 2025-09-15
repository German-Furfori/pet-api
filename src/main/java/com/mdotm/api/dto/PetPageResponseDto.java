package com.mdotm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PetPageResponseDto {
    private List<PetResponseDto> content;
    private int page;
    private int size;
    private long total;
}
