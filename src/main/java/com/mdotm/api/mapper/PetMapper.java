package com.mdotm.api.mapper;

import com.mdotm.api.dto.PetResponseDto;
import com.mdotm.api.entity.Pet;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {
    List<PetResponseDto> petPageToPetResponseDtoPage(Page<Pet> petPage);
}