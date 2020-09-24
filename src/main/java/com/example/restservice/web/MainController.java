package com.example.restservice.web;

import com.example.restservice.common.EntityType;
import com.example.restservice.web.model.ActionResult;
import com.example.restservice.web.model.Stats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Rest API Entry point
 */
@RestController
public class MainController {

    /**
     * Count API requests by {@link EntityType}
     */
    private final ConcurrentHashMap<EntityType, AtomicLong> requestCounters = new ConcurrentHashMap<>();


    @PostMapping("/api/{type}")
    public List<ActionResult> performActionsForType(final @PathVariable(value = "type") EntityType type) {
        requestCounters.putIfAbsent(type, new AtomicLong(0));
        requestCounters.get(type).incrementAndGet();

        //TODO impl

        final List<ActionResult> results = new ArrayList<>();
        return results;
    }

    @GetMapping("/requests/stats")
    public Stats requestsStats() {
        Stats stats = new Stats();
        stats.setEvent(requestCounters.getOrDefault(EntityType.EVENT, new AtomicLong(0)).get());
        stats.setFact(requestCounters.getOrDefault(EntityType.FACT, new AtomicLong(0)).get());
        stats.setObservation(requestCounters.getOrDefault(EntityType.OBSERVATION, new AtomicLong(0)).get());
        return stats;
    }
}
