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
    private final String defaultPage = "0";
    private final String defaultSize = "4";

    @Test
    @SneakyThrows
    void findAllPets_withData_returnPage() {
        this.generatePetInDB("Luna", "Dog");
        this.generatePetInDB("Milo", "Cat");

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
                .andExpect(jsonPath("$.content[0].name").value("Luna"))
                .andExpect(jsonPath("$.content[0].species").value("Dog"))
                .andExpect(jsonPath("$.content[1].name").value("Milo"))
                .andExpect(jsonPath("$.content[1].species").value("Cat"))
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
}