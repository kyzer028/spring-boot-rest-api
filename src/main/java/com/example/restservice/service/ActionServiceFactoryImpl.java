package com.example.restservice.service;

import com.example.restservice.model.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Default implementation of {@link ActionServiceFactory}
 */
@Service
public class ActionServiceFactoryImpl implements ActionServiceFactory {

    /**
     * Injected ActionServices
     */
    private final Map<String, List<ActionService>> actionsByEntity;

    /**
     * Default constructor that handles injected {@link ActionService}
     * - Associate all action services by entity type
     * - Order action services by execution priority
     *
     * @param actionServices injected action services
     */
    @Autowired
    public ActionServiceFactoryImpl(List<ActionService> actionServices) {

        actionsByEntity = actionServices.stream()
                .sorted(Comparator.comparingInt(ActionService::getPriority))
                .collect(Collectors.groupingBy((ActionService t) -> t.getType().getName()));
    }

    @Override
    public <T extends BaseEntity> List<ActionService> getActionsServicesForEntity(final Class<T> entityClass) {
        return actionsByEntity.get(entityClass.getName());
    }


}