package lirosk.springrestauth.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import lirosk.springrestauth.controllers.utils.WithCustomMockUser;
import lirosk.springrestauth.dto.AuthRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    private final String username = "buzz";
    private final String password = "qwe";
    private final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Autowired
    private MockMvc api;

    @Test
    void notRegistered_successfulRegistration() throws Exception {
        api.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(new AuthRequest(username, password)))
                )
                .andExpect(status().isCreated());
    }

    @Test
    @WithCustomMockUser(username = username, password = password)
    void registered_failedRegistration() throws Exception {
        api.perform(
                        post("/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(new AuthRequest(username, password)))
                )
                .andExpect(status().isConflict());
    }
}
