package com.mdotm.api.service;

import com.mdotm.api.dto.PetPageResponseDto;
import com.mdotm.api.dto.PetRequestDto;
import com.mdotm.api.dto.PetResponseDto;
import org.springframework.data.domain.Pageable;

public interface PetService {
    PetPageResponseDto findAll(Pageable pageable);
    PetResponseDto findById(Long id);
    PetResponseDto createPet(PetRequestDto petRequest);
    PetResponseDto updatePet(Long id, PetRequestDto petRequest);
    void deletePetById(Long id);
}