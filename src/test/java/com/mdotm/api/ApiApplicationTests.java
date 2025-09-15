package com.mdotm.api;

import com.mdotm.api.entity.Pet;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;
import java.io.File;
import java.io.FileReader;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
public abstract class ApiApplicationTests {
	@Autowired
	protected MockMvc mockMvc;
	@Autowired
	protected EntityManager entityManager;
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@BeforeEach
	void resetDatabase() {
		jdbcTemplate.execute("DELETE FROM pets");
		jdbcTemplate.execute("ALTER TABLE pets ALTER COLUMN id RESTART WITH 1");
	}

	@SneakyThrows
	protected String getContentFromFile(String filePath) {
		File file = new ClassPathResource(filePath).getFile();
		return FileCopyUtils.copyToString(new FileReader(file));
	}

	protected void generatePetInDB(String name, String species, Integer age, String ownerName) {
		Pet pet = new Pet();
		pet.setName(name);
		pet.setSpecies(species);
		pet.setAge(age);
		pet.setOwnerName(ownerName);

		entityManager.persist(pet);
		entityManager.flush();
	}

	protected void generateRandomPetsInDB() {
		this.generatePetInDB("Luna", "Dog", 3, "Helen Schneider");
		this.generatePetInDB("Milo", "Cat", 2, "Laura Herrera");
	}

	protected Boolean verifyStringInPetsTable(String column, String value) {
		String whereClause = column + " = '" + value + "'";
		int count = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "pets", whereClause);
		return count == 1;
	}

	protected Boolean verifyNumberInPetsTable(String column, Long value) {
		String whereClause = column + " = " + value;
		int count = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "pets", whereClause);
		return count == 1;
	}
}