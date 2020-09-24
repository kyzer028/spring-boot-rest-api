package com.example.restservice.web.model;


/**
 * Request statistics API model object
 */
public class Stats {

    /**
     * number of api calls for type "OBSERVATION"
     */
    long observation;
    /**
     * number of api calls for type "FACT"
     */
    long fact;

    /**
     * number of api calls for type "EVENT"
     */
    long event;

    /**
     * Gets observation.
     *
     * @return value of observation
     */
    public long getObservation() {
        return observation;
    }

    /**
     * Sets new observation.
     *
     * @param observation new value of observation
     */
    public void setObservation(final long observation) {
        this.observation = observation;
    }

    /**
     * Gets fact.
     *
     * @return value of fact
     */
    public long getFact() {
        return fact;
    }

    /**
     * Sets new fact.
     *
     * @param fact new value of fact
     */
    public void setFact(final long fact) {
        this.fact = fact;
    }

    /**
     * Gets event.
     *
     * @return value of event
     */
    public long getEvent() {
        return event;
    }

    /**
     * Sets new event.
     *
     * @param event new value of event
     */
    public void setEvent(final long event) {
        this.event = event;
    }
}
