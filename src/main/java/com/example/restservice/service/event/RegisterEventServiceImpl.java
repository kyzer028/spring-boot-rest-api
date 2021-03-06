package com.example.restservice.service.event;

import com.example.restservice.model.Event;
import com.example.restservice.service.ActionService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Event Registration Action Implementation
 */
@Service("RegisterEventService")
public final class RegisterEventServiceImpl implements ActionService<Event> {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterEventServiceImpl.class);

    /**
     * Service priority level
     */
    private static final int PRIORITY_LEVEL = 2;

    @Override
    public int getPriority() {
        return PRIORITY_LEVEL;
    }

    @Override
    public void perform(final Event entity) {
        LOGGER.info("Perform 'REGISTER' action on 'EVENT' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Event> getType() {
        return Event.class;
    }


}

