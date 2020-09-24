package com.example.restservice.service;

import com.example.restservice.model.BaseEntity;

/**
 * Define an action service behaviour for a given entity
 *
 * @param <T> entity type that should extends {@link BaseEntity}
 */
public interface ActionService<T extends BaseEntity> {
    /**
     * Action service execution order, low number firsts
     *
     * @return execution order
     */
    int getPriority();

    /**
     * Perform service action on the given entity
     *
     * @param entity target entity for which the action is performed to
     */
    void perform(T entity);

    /**
     * Action entity concrete class type
     *
     * @return entity type that should extends {@link BaseEntity}
     */
    Class<T> getType();
}
