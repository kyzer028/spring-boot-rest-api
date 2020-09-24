package com.example.restservice.common;

import com.example.restservice.model.BaseEntity;
import com.example.restservice.model.Event;
import com.example.restservice.model.Fact;
import com.example.restservice.model.Observation;

/**
 * Defines all available entity Types managed by this APP
 */
public enum EntityType {

    OBSERVATION(Observation.class),
    FACT(Fact.class),
    EVENT(Event.class);

    /**
     * Concrete entity class linked to the type
     */
    private final Class<? extends BaseEntity> classType;

    /**
     * Default constructor
     *
     * @param classType Concrete entity class linked to the type
     */
    EntityType(final Class<? extends BaseEntity> classType) {
        this.classType = classType;
    }

    /**
     * Gets class type.
     *
     * @return Concrete entity class linked to the type
     */
    public Class<? extends BaseEntity> getClassType() {
        return classType;
    }
}
