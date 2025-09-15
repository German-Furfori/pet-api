package com.mdotm.api.service.impl;

import com.mdotm.api.dto.PetPageResponseDto;
import com.mdotm.api.dto.PetResponseDto;
import com.mdotm.api.entity.Pet;
import com.mdotm.api.mapper.PetMapper;
import com.mdotm.api.repository.PetRepository;
import com.mdotm.api.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Override
    public PetPageResponseDto findAll(Pageable pageable) {
        Page<Pet> petPage = petRepository.findAll(pageable);
        List<PetResponseDto> petList = petMapper.petPageToPetResponseDtoPage(petPage);
        return new PetPageResponseDto(petList,
                petPage.getPageable().getPageNumber(),
                petPage.getPageable().getPageSize(),
                petPage.getTotalElements());
    }
}