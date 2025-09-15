package com.mdotm.api.mapper;

import com.mdotm.api.dto.PetRequestDto;
import com.mdotm.api.dto.PetResponseDto;
import com.mdotm.api.entity.Pet;
import com.mdotm.api.utils.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper(componentModel = "spring",
        uses = MapperUtils.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PetMapper {
    List<PetResponseDto> petPageToPetResponseDtoPage(Page<Pet> petPage);
    PetResponseDto petToPetResponseDto(Pet pet);
    Pet petRequestToPet(PetRequestDto petRequest);
    void updatePetWithPetRequest(PetRequestDto petRequest, @MappingTarget Pet pet);
}