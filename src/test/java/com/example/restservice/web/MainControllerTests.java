package com.example.restservice.web;

import com.example.restservice.common.EntityType;
import com.example.restservice.model.Observation;
import com.example.restservice.service.ActionService;
import com.example.restservice.service.ActionServiceFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.hasSize;
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

    // Service layer is mocked by Mockito
    @MockBean
    private ActionServiceFactory actionServiceFactory;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void clearRequestsCounter() {
        mainController.clearRequestStats();
    }

    @Test
    void testPerformActionForType() throws Exception {
        //
        // Prepare service mock
        //
        final ActionService observationActionMock1 = Mockito.mock(ActionService.class);
        final ActionService observationActionMock2 = new ObservationActionTest();

        Mockito.when(actionServiceFactory.getActionsServicesForEntity(Mockito.eq(Observation.class)))
                .thenReturn(Arrays.asList(observationActionMock1, observationActionMock2));

        //
        // perform API call
        //
        mockMvc.perform(post("/api/" + EntityType.OBSERVATION.name()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name").value(observationActionMock1.getClass().getSimpleName()))
                .andExpect(jsonPath("$.[0].order").value(0)) //Order is not defined because it is a mocked instance
                .andExpect(jsonPath("$.[1].name").value(ObservationActionTest.class.getSimpleName()))
                .andExpect(jsonPath("$.[1].order").value(12));

        //
        //Verifications
        //
        Mockito.verify(actionServiceFactory).getActionsServicesForEntity(Mockito.eq(Observation.class));

        ArgumentCaptor<Observation> observationEntityCaptor = ArgumentCaptor.forClass(Observation.class);

        // Verify that perform() method has been executed on each observation action service
        Mockito.verify(observationActionMock1).perform(observationEntityCaptor.capture());
        Assertions.assertThat(((ObservationActionTest) observationActionMock2).getPerformCounter()).isEqualTo(1);

        // Check captured entity, generated with random values in controller
        final Observation capturedObs = observationEntityCaptor.getValue();
        Assertions.assertThat(capturedObs).isNotNull();
        Assertions.assertThat(capturedObs.getValue()).isNotNull();
        Assertions.assertThat(capturedObs.getDate()).isPositive();

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

        // test API calls with concurrency
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        executor.submit(() -> {
            for (int i = 0; i < nbObservationApiCalls; i++) {
                mockMvc.perform(post("/api/" + EntityType.OBSERVATION.name()));
            }
            return "OBSERVATION DONE";
        });
        executor.submit(() -> {
            for (int i = 0; i < nbFactApiCalls; i++) {
                mockMvc.perform(post("/api/" + EntityType.FACT.name()));
            }
            return "FACT DONE";
        });
        executor.submit(() -> {
            for (int i = 0; i < nbEventApiCalls; i++) {
                mockMvc.perform(post("/api/" + EntityType.EVENT.name()));
            }
            return "EVENT DONE";
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        this.mockMvc.perform(get("/requests/stats").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("observation").value(nbObservationApiCalls))
                .andExpect(jsonPath("fact").value(nbFactApiCalls))
                .andExpect(jsonPath("event").value(nbEventApiCalls));
    }


    public static final class ObservationActionTest implements ActionService<Observation> {
        private int performCounter;

        @Override
        public int getPriority() {
            return 12;
        }

        @Override
        public void perform(final Observation entity) {
            performCounter++;
        }

        @Override
        public Class<Observation> getType() {
            return null;
        }

        public int getPerformCounter() {
            return performCounter;
        }
    }
}
