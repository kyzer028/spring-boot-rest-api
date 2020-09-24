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
public class DefaultEventServiceImpl implements ActionService<Event> {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultEventServiceImpl.class);

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void perform(final Event entity) {
        logger.info("Perform 'DEFAULT' action on 'EVENT' entity : {}", ReflectionToStringBuilder.toString(entity));
    }

    @Override
    public Class<Event> getType() {
        return Event.class;
    }


}

