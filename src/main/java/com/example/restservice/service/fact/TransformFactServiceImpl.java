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
public class TransformFactServiceImpl implements ActionService<Fact> {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TransformFactServiceImpl.class);

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void perform(final Fact entity) {
        logger.info("Perform 'TRANSFORM' action on 'FACT' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Fact> getType() {
        return Fact.class;
    }


}

