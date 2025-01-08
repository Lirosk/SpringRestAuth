package lirosk.springrestauth.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import lirosk.springrestauth.controllers.utils.WithCustomMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MeControllerTest {
    @Autowired
    private MockMvc api;

    @Test
    void notLoggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        api.perform(get("/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithCustomMockUser
    void loggedIn_shouldSeeSecuredEndpoint() throws Exception {
        api.perform(get("/me"))
                .andExpect(status().isOk());
    }
}
