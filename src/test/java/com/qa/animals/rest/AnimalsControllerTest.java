package com.qa.animals.rest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
@AutoConfigureMockMvc //acts like your postman - makes the test requests
@Sql(scripts = {"classpath:animals-schema.sql","classpath:animals-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)// PRE-POPULATE OUT H2 DB
@ActiveProfiles("test")//run this test class on the db in the application-test.properties
public class AnimalsControllerTest {
	
	@Autowired
	private MockMvc mock; //TO MOCK THE REQUESTS

	@Autowired
	private ObjectMapper map;  //Interprets JSON
	
	@Test
	void testCreate() throws Exception {
		//--request
		// type, url, body?
		//body - JSON - Object
		Animals create = new Animals("Bird",5,"M");//object
		//convert into JSON STRING
		String createJSON =this.map.writeValueAsString(create);
		//build up the mock request
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		//--response
		// body(JSON with id)
		Animals saved = new Animals(2L, "Bird",5,"M");
		//convert into JSON STRING
		String savedJSON =this.map.writeValueAsString(saved);
		//--test response is correct (status + body)
		//status = 201 CREATED
		ResultMatcher matchStatus = status().isCreated();
		//TEST RESPONSE BODY
		ResultMatcher matchBody = content().json(savedJSON);
		//perform the test
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void testRemove() throws Exception {
		//--request - type-delete, path param-id, url
		Long id = 1L;
		RequestBuilder mockRequest = delete("/remove/?id="+id);
		//--response - status, boolean-body
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().string("true");
		//---test
		this.mock.perform(mockRequest).andExpect(body).andExpect(status);
	}
	
	
	
}
