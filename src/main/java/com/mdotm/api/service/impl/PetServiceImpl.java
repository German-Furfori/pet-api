package com.mdotm.api.service.impl;

import com.mdotm.api.dto.PetPageResponseDto;
import com.mdotm.api.dto.PetRequestDto;
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
import java.util.NoSuchElementException;

import static com.mdotm.api.constant.ErrorMessages.NON_EXISTING_PET;

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

    @Override
    public PetResponseDto findById(Long id) {
        Pet pet = this.getPetById(id);
        return petMapper.petToPetResponseDto(pet);
    }

    @Override
    public PetResponseDto createPet(PetRequestDto petRequest) {
        Pet pet = petMapper.petRequestToPet(petRequest);
        petRepository.save(pet);
        return petMapper.petToPetResponseDto(pet);
    }

    @Override
    public PetResponseDto updatePet(Long id, PetRequestDto petRequest) {
        Pet pet = this.getPetById(id);
        petMapper.updatePetWithPetRequest(petRequest, pet);
        petRepository.save(pet);
        return petMapper.petToPetResponseDto(pet);
    }

    @Override
    public void deleteById(Long id) {
        Pet pet = this.getPetById(id);
        petRepository.delete(pet);
    }

    private Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(NON_EXISTING_PET, id)));
    }
}