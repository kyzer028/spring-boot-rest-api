package com.example.restservice.service.observation;

import com.example.restservice.model.Observation;
import com.example.restservice.service.ActionService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Observation Computing Action Implementation
 */
@Service("ComputeObservationService")
public class ComputeObservationServiceImpl implements ActionService<Observation> {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ComputeObservationServiceImpl.class);

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void perform(final Observation entity) {
        logger.info("Perform 'COMPUTING' action on 'OBSERVATION' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Observation> getType() {
        return Observation.class;
    }

}

