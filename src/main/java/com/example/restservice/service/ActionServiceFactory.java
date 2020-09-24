package com.example.restservice.service;

import com.example.restservice.model.BaseEntity;

import java.util.List;

/**
 * Action Service Factory.
 * Handle all Injected {@link ActionService} implementation associated to each domain entity type
 */
public interface ActionServiceFactory {

    /**
     * Retrieve all injected ActionService implementations associated to the given entity type
     *
     * @param entityClass domain entity concrete class
     * @param <T>         domain entity concrete type
     * @return List of ActionService
     */
    <T extends BaseEntity> List<ActionService> getActionsServicesForEntity(Class<T> entityClass);

}
