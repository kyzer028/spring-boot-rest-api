package com.example.restservice.service.event;

import com.example.restservice.model.Event;
import com.example.restservice.service.ActionService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Event Default Action Implementation, the one with the lowest priority
 */
@Service("DefaultEventService")
public final class DefaultEventServiceImpl implements ActionService<Event> {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEventServiceImpl.class);

    /**
     * Service priority level
     */
    private static final int PRIORITY_LEVEL = 1;

    @Override
    public int getPriority() {
        return PRIORITY_LEVEL;
    }

    @Override
    public void perform(final Event entity) {
        LOGGER.info("Perform 'DEFAULT' action on 'EVENT' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Event> getType() {
        return Event.class;
    }


}

