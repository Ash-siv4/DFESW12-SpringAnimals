package com.qa.animals.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
@AutoConfigureMockMvc // acts like your postman - makes the test requests
@Sql(scripts = { "classpath:animals-schema.sql",
		"classpath:animals-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) // PRE-POPULATE OUT H2
																								// DB
@ActiveProfiles("test") // run this test class on the db in the application-test.properties
public class AnimalsControllerTest {

	@Autowired
	private MockMvc mock; // TO MOCK THE REQUESTS

	@Autowired
	private ObjectMapper map; // Interprets JSON

	@Test
	void testCreate() throws Exception {
		// --request
		// type, url, body?
		// body - JSON - Object
		Animals create = new Animals("Bird", 5, "M");// object
		// convert into JSON STRING
		String createJSON = this.map.writeValueAsString(create);
		// build up the mock request
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		// --response
		// body(JSON with id)
		Animals saved = new Animals(4L, "Bird", 5, "M");
		// convert into JSON STRING
		String savedJSON = this.map.writeValueAsString(saved);
		// --test response is correct (status + body)
		// status = 201 CREATED
		ResultMatcher matchStatus = status().isCreated();
		// TEST RESPONSE BODY
		ResultMatcher matchBody = content().json(savedJSON);
		// perform the test
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void readTest() throws Exception {
		// db values
		Animals a = new Animals(1L, "Mammal", 4, "f");
		Animals b = new Animals(2L, "Reptile", 2, "m");
		Animals c = new Animals(3L, "Sea-creature", 6, "f");
		// store in list
		List<Animals> db = List.of(a, b, c);
		// convert to JSON
		String dbJSON = this.map.writeValueAsString(db);
		// Build request
		RequestBuilder mockReq = get("/readAll");
		// --response
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(dbJSON);
		// test
		this.mock.perform(mockReq).andExpect(body).andExpect(status);
	}
	

	@Test
	void testDelete() throws Exception {
		// --request - type-delete, path param-id, url
		Long id = 2L;
		RequestBuilder mockRequest = delete("/delete/?id=" + id);
		// --response - status, boolean-body
		// body(JSON with id)
		Animals deleted = new Animals(2L, "Reptile", 2, "m");
		// convert into JSON STRING
		String deletedJSON = this.map.writeValueAsString(deleted);
		// --test
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(deletedJSON);
		this.mock.perform(mockRequest).andExpect(body).andExpect(status);
	}

	@Test
	void testRemove() throws Exception {
		// --request - type-delete, path param-id, url
		Long id = 1L;
		RequestBuilder mockRequest = delete("/remove/?id=" + id);
		// --response - status, boolean-body
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().string("true");
		// ---test
		this.mock.perform(mockRequest).andExpect(body).andExpect(status);
	}

}
