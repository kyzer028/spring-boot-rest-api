package com.example.restservice.service.observation;

import com.example.restservice.model.Observation;
import com.example.restservice.service.ActionService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("PublishObservationService")
public class PublishObservationServiceImpl implements ActionService<Observation> {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(PublishObservationServiceImpl.class);

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void perform(final Observation entity) {
        logger.info("Perform 'PUBLISH' action on 'OBSERVATION' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Observation> getType() {
        return Observation.class;
    }


}

