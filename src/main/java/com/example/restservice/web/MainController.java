package com.example.restservice.web;

import com.example.restservice.common.EntityType;
import com.example.restservice.model.BaseEntity;
import com.example.restservice.model.Event;
import com.example.restservice.model.Fact;
import com.example.restservice.model.Observation;
import com.example.restservice.service.ActionServiceFactory;
import com.example.restservice.web.model.ActionResult;
import com.example.restservice.web.model.Stats;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Rest API Entry point
 */
@RestController
public final class MainController {

    /**
     * Count API requests by {@link EntityType}
     */
    private final ConcurrentHashMap<EntityType, AtomicLong> requestCounters = new ConcurrentHashMap<>();

    /**
     * Injected Action Service Factory
     */
    private final ActionServiceFactory actionServiceFactory;

    /**
     * Main constructor
     *
     * @param service ActionServiceFactory to inject
     */
    @Autowired
    public MainController(final ActionServiceFactory service) {
        this.actionServiceFactory = service;
    }

    /**
     * Perform injected actions for a given entity type
     *
     * @param type entity type OBSERVATION/FACT/EVENT
     * @return an array of performed action results (class implementation name and order)
     */
    @PostMapping("/api/{type}")
    public List<ActionResult> performActionsForType(final @PathVariable(value = "type") EntityType type) {
        requestCounters.putIfAbsent(type, new AtomicLong(0));
        requestCounters.get(type).incrementAndGet();

        // For demo purpose, the target entity in generated randomly
        BaseEntity entityToHandle = null;

        switch (type) {
            case OBSERVATION:
                entityToHandle = new Observation();
                entityToHandle.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
                ((Observation) entityToHandle).setDate(System.currentTimeMillis());
                ((Observation) entityToHandle).setValue(ThreadLocalRandom.current().nextDouble());
                break;
            case EVENT:
                entityToHandle = new Event();
                entityToHandle.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
                ((Event) entityToHandle).setDate(System.currentTimeMillis());
                ((Event) entityToHandle).setContent(RandomStringUtils.randomAlphabetic(30));
                break;
            case FACT:
                entityToHandle = new Fact();
                entityToHandle.setId(ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE));
                ((Fact) entityToHandle).setName(RandomStringUtils.randomAlphabetic(5));
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type " + type + " not yet supported");
        }

        final List<ActionResult> results = new ArrayList<>();

        final BaseEntity finalEntityToHandle = entityToHandle;
        // perform all actions services to the given entity
        actionServiceFactory.getActionsServicesForEntity(type.getClassType()).forEach(service -> {
            service.perform(finalEntityToHandle);
            results.add(new ActionResult(service.getClass().getSimpleName(), service.getPriority()));
        });

        return results;
    }

    /**
     * Display requests counter by entity type
     *
     * @return a structured object with requests counter
     */
    @GetMapping("/requests/stats")
    public Stats requestsStats() {
        Stats stats = new Stats();
        stats.setEvent(requestCounters.getOrDefault(EntityType.EVENT, new AtomicLong(0)).get());
        stats.setFact(requestCounters.getOrDefault(EntityType.FACT, new AtomicLong(0)).get());
        stats.setObservation(requestCounters.getOrDefault(EntityType.OBSERVATION, new AtomicLong(0)).get());
        return stats;
    }

    /**
     * Convenient method to reset requests counter
     */
    public void clearRequestStats() {
        this.requestCounters.clear();
    }
}
