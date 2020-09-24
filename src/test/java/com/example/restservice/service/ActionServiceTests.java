package com.example.restservice.service;

import com.example.restservice.model.Event;
import com.example.restservice.model.Fact;
import com.example.restservice.model.Observation;
import com.example.restservice.service.event.DefaultEventServiceImpl;
import com.example.restservice.service.event.RegisterEventServiceImpl;
import com.example.restservice.service.fact.DefaultFactServiceImpl;
import com.example.restservice.service.fact.TransformFactServiceImpl;
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
        assertThat(actions.get(0).getPriority()).isEqualTo(1);
        assertThat(actions.get(0).getType()).isEqualTo(Observation.class);
        assertThat(actions.get(1)).isInstanceOf(ComputeObservationServiceImpl.class);
        assertThat(actions.get(1).getPriority()).isEqualTo(2);
        assertThat(actions.get(1).getType()).isEqualTo(Observation.class);
        assertThat(actions.get(2)).isInstanceOf(PublishObservationServiceImpl.class);
        assertThat(actions.get(2).getPriority()).isEqualTo(3);
        assertThat(actions.get(2).getType()).isEqualTo(Observation.class);
    }

    @Test
    public void testRetrieveActionClassImplementationForFactEntityType() {
        final List<ActionService> actions = actionServiceFactory.getActionsServicesForEntity(Fact.class);

        //Verify ordered implementations of entity type Fact
        assertThat(actions)
                .withFailMessage("wrong number of Fact services class implementation")
                .hasSize(2);

        assertThat(actions.get(0)).isInstanceOf(DefaultFactServiceImpl.class);
        assertThat(actions.get(0).getPriority()).isEqualTo(1);
        assertThat(actions.get(0).getType()).isEqualTo(Fact.class);
        assertThat(actions.get(1)).isInstanceOf(TransformFactServiceImpl.class);
        assertThat(actions.get(1).getPriority()).isEqualTo(2);
        assertThat(actions.get(1).getType()).isEqualTo(Fact.class);
    }

    @Test
    public void testRetrieveActionClassImplementationForEventEntityType() {
        final List<ActionService> actions = actionServiceFactory.getActionsServicesForEntity(Event.class);

        //Verify ordered implementations of entity type Event
        assertThat(actions)
                .withFailMessage("wrong number of Event services class implementation")
                .hasSize(2);

        assertThat(actions.get(0)).isInstanceOf(DefaultEventServiceImpl.class);
        assertThat(actions.get(0).getPriority()).isEqualTo(1);
        assertThat(actions.get(0).getType()).isEqualTo(Event.class);
        assertThat(actions.get(1)).isInstanceOf(RegisterEventServiceImpl.class);
        assertThat(actions.get(1).getPriority()).isEqualTo(2);
        assertThat(actions.get(1).getType()).isEqualTo(Event.class);
    }
}
