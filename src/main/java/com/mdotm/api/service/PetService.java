package com.mdotm.api.service;

import com.mdotm.api.dto.PetPageResponseDto;
import com.mdotm.api.dto.PetResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetService {
    PetPageResponseDto findAll(Pageable pageable);
}