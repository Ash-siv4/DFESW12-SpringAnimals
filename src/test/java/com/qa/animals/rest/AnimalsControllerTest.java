package com.qa.animals.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.animals.domain.Animals;

@SpringBootTest
@AutoConfigureMockMvc // act like out postman
@Sql(scripts = { "classpath:animals-schema.sql",
		"classpath:animals-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // point to application-test.properties
public class AnimalsControllerTest {

	@Autowired
	private MockMvc mock;// to mock the requests

	@Autowired
	private ObjectMapper map; // Interprets JSON

	@Test
	void createTest() throws Exception {
		// --request-> type, url, body? -> send
		// body - object convert to JSON
		Animals create = new Animals("Bird", 5, "m");
		// convert to JSON string
		String createJson = this.map.writeValueAsString(create);
		// Build up the request
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(createJson);

		// --response -> body and the status
		// body - object convert to JSON
		Animals saved = new Animals(2L, "Bird", 5, "m");
		// convert to JSON string
		String savedJson = this.map.writeValueAsString(saved);

		// --test - check status and body
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedJson);

		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

		// to do it all in one line
//		this.mock
//				.perform(post("/create").contentType(MediaType.APPLICATION_JSON)
//						.content(this.map.writeValueAsString(new Animals("Bird", 5, "m"))))
//				.andExpect(status().isCreated())
//				.andExpect(content().json(this.map.writeValueAsString(new Animals(2L, "Bird", 5, "m"))));

	}

}
