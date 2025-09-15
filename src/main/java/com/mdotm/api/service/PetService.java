package com.mdotm.api.service;

import com.mdotm.api.dto.PetPageResponseDto;
import com.mdotm.api.dto.PetResponseDto;
import org.springframework.data.domain.Pageable;

public interface PetService {
    PetPageResponseDto findAll(Pageable pageable);
    PetResponseDto findById(Long id);
}