package com.mdotm.api.controller;

import com.mdotm.api.dto.PetPageResponseDto;
import com.mdotm.api.dto.PetRequestDto;
import com.mdotm.api.dto.PetResponseDto;
import com.mdotm.api.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public PetPageResponseDto findAllPets(Pageable pageable) {
        log.info("[PetController] findAllPets request: [{}]", pageable);
        PetPageResponseDto petPages = petService.findAll(pageable);
        log.info("[PetController] findAllPets response: [{}]", petPages.getTotal());
        return petPages;
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public PetResponseDto findPetById(@PathVariable Long id) {
        log.info("[PetController] findPetById request: [{}]", id);
        PetResponseDto petResponse = petService.findById(id);
        log.info("[PetController] findPetById response: [{}]", petResponse);
        return petResponse;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public PetResponseDto createPet(@Valid @RequestBody PetRequestDto petRequest) {
        log.info("[PetController] createPet request: [{}]", petRequest);
        PetResponseDto petResponse = petService.createPet(petRequest);
        log.info("[PetController] createPet response: [{}]", petResponse);
        return petResponse;
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    public PetResponseDto updatePet(@PathVariable Long id, @RequestBody PetRequestDto petRequest) {
        log.info("[PetController] updatePet request: [{}], id: [{}]", petRequest, id);
        PetResponseDto petResponse = petService.updatePet(id, petRequest);
        log.info("[PetController] updatePet response: [{}]", petResponse);
        return petResponse;
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable Long id) {
        log.info("[PetController] deleteById request: [{}]", id);
        petService.deletePetById(id);
        log.info("[PetController] deleteById finished");
    }
}