package com.example.restservice.service.fact;

import com.example.restservice.model.Fact;
import com.example.restservice.service.ActionService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Fact Transformation Action Implementation
 */
@Service("TransformFactService")
public final class TransformFactServiceImpl implements ActionService<Fact> {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransformFactServiceImpl.class);

    /**
     * Service priority level
     */
    private static final int PRIORITY_LEVEL = 2;

    @Override
    public int getPriority() {
        return PRIORITY_LEVEL;
    }

    @Override
    public void perform(final Fact entity) {
        LOGGER.info("Perform 'TRANSFORM' action on 'FACT' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Fact> getType() {
        return Fact.class;
    }


}

