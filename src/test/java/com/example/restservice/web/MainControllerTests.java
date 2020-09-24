package com.example.restservice.web;

import com.example.restservice.common.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link MainController}
 */
@WebMvcTest(controllers = MainController.class)
class MainControllerTests {

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clearRequestsCounter() {
        mainController.clearRequestStats();
    }

    @Test
    void testPerformActionForType() throws Exception {

        mockMvc.perform(post("/api/" + EntityType.OBSERVATION.name()))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.content").value("Hello, World!"));

    }


    @Test
    public void testGetRequestStatsWithoutAnyCalls() throws Exception {

        this.mockMvc.perform(get("/requests/stats").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("observation").value(0))
                .andExpect(jsonPath("fact").value(0))
                .andExpect(jsonPath("event").value(0));
    }

    @Test
    public void testGetRequestStatsWithSeveralCalls() throws Exception {
        final int nbObservationApiCalls = 10;
        final int nbFactApiCalls = 5;
        final int nbEventApiCalls = 3;

        for (int i = 0; i < nbObservationApiCalls; i++) {
            mockMvc.perform(post("/api/" + EntityType.OBSERVATION.name()));
        }

        for (int i = 0; i < nbFactApiCalls; i++) {
            mockMvc.perform(post("/api/" + EntityType.FACT.name()));
        }

        for (int i = 0; i < nbEventApiCalls; i++) {
            mockMvc.perform(post("/api/" + EntityType.EVENT.name()));
        }

        this.mockMvc.perform(get("/requests/stats").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("observation").value(nbObservationApiCalls))
                .andExpect(jsonPath("fact").value(nbFactApiCalls))
                .andExpect(jsonPath("event").value(nbEventApiCalls));
    }

}
