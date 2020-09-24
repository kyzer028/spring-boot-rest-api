package com.example.restservice.service.fact;

import com.example.restservice.model.Fact;
import com.example.restservice.service.ActionService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Fact Default Action Implementation, the one with the lowest priority
 */
@Service("DefaultFactService")
public class DefaultFactServiceImpl implements ActionService<Fact> {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultFactServiceImpl.class);

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void perform(final Fact entity) {
        logger.info("Perform 'DEFAULT' action on 'FACT' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Fact> getType() {
        return Fact.class;
    }


}

