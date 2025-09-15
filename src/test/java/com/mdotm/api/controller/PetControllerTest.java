package com.mdotm.api.controller;

import com.mdotm.api.ApiApplicationTests;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class PetControllerTest extends ApiApplicationTests {
    private final String pathPets = "/pets";
    private final String pathGetById = "/{id}";
    private final String defaultPage = "0";
    private final String defaultSize = "4";
    private final Integer defaultId = 1;

    @Test
    @SneakyThrows
    void findAllPets_withData_returnPage() {
        this.generateRandomPetsInDB();

        mockMvc
                .perform(get(pathPets)
                        .param("page", defaultPage)
                        .param("size", defaultSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.page").exists())
                .andExpect(jsonPath("$.size").exists())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Luna"))
                .andExpect(jsonPath("$.content[0].species").value("Dog"))
                .andExpect(jsonPath("$.content[0].age").value(3))
                .andExpect(jsonPath("$.content[0].ownerName").value("Helen Schneider"))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].name").value("Milo"))
                .andExpect(jsonPath("$.content[1].species").value("Cat"))
                .andExpect(jsonPath("$.content[1].age").value(2))
                .andExpect(jsonPath("$.content[1].ownerName").value("Laura Herrera"))
                .andExpect(jsonPath("$.page").value("0"))
                .andExpect(jsonPath("$.size").value("4"))
                .andExpect(jsonPath("$.total").value("2"));
    }

    @Test
    @SneakyThrows
    void findAllPets_withoutData_returnPage() {
        mockMvc
                .perform(get(pathPets)
                        .param("page", defaultPage)
                        .param("size", defaultSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.page").exists())
                .andExpect(jsonPath("$.size").exists())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.page").value("0"))
                .andExpect(jsonPath("$.size").value("4"))
                .andExpect(jsonPath("$.total").value("0"));
    }

    @Test
    @SneakyThrows
    void findPetById_withValidId_returnPet() {
        this.generateRandomPetsInDB();

        mockMvc
                .perform(get(pathPets.concat(pathGetById), defaultId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.species").exists())
                .andExpect(jsonPath("$.age").exists())
                .andExpect(jsonPath("$.ownerName").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Luna"))
                .andExpect(jsonPath("$.species").value("Dog"))
                .andExpect(jsonPath("$.age").value(3))
                .andExpect(jsonPath("$.ownerName").value("Helen Schneider"));
    }

    @Test
    @SneakyThrows
    void findPetById_withInvalidId_returnNotFound() {
        mockMvc
                .perform(get(pathPets.concat(pathGetById), defaultId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.code").value("404 NOT_FOUND"))
                .andExpect(jsonPath("$.description").value("Pet with id 1 not found"));
    }
}