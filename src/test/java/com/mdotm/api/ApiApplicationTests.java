package com.mdotm.api;

import com.mdotm.api.entity.Pet;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
public abstract class ApiApplicationTests {
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected EntityManager entityManager;

	protected void generatePetInDB(String name, String species) {
		Pet pet = new Pet();
		pet.setName(name);
		pet.setSpecies(species);

		entityManager.persist(pet);
		entityManager.flush();
	}
}