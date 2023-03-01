package com.mycompany.conferencedemo;

import com.mycompany.conferencedemo.models.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
	@MockBean
	private Session session;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("RBC Assignment - Q1")
	void contextLoads() throws Exception {
		Session mockSession = new Session();
		doReturn(Optional.of(mockSession)).when(session).getSession_id();

		mockMvc.perform(get("{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/sessions/1"))
				.andExpect(jsonPath("$.session_id").value(1L));


	}

}

