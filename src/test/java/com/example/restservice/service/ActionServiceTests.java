package com.example.restservice.service;

import com.example.restservice.model.Observation;
import com.example.restservice.service.observation.ComputeObservationServiceImpl;
import com.example.restservice.service.observation.DefaultObservationServiceImpl;
import com.example.restservice.service.observation.PublishObservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link ActionServiceFactory}
 */
@SpringBootTest
public class ActionServiceTests {

    @Autowired
    protected ActionServiceFactory actionServiceFactory;

    @Test
    public void testRetrieveActionClassImplementationForObservationEntityType() {
        final List<ActionService> actions = actionServiceFactory.getActionsServicesForEntity(Observation.class);

        //Verify ordered implementations of entity type Observation
        assertThat(actions)
                .withFailMessage("wrong number of Observation services class implementation")
                .hasSize(3);

        assertThat(actions.get(0)).isInstanceOf(DefaultObservationServiceImpl.class);
        assertThat(actions.get(1)).isInstanceOf(ComputeObservationServiceImpl.class);
        assertThat(actions.get(2)).isInstanceOf(PublishObservationServiceImpl.class);
    }


}
