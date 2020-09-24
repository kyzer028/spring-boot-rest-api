package com.example.restservice.model;

/**
 * Observation domain object
 */
public class Observation extends BaseEntity {

    /**
     * Observation value
     */
    private Double value;

    /**
     * Observation date unix ts
     */
    private long date;

    /**
     * Gets date.
     *
     * @return value of date
     */
    public long getDate() {
        return date;
    }

    /**
     * Sets new date.
     *
     * @param date new value of date
     */
    public void setDate(final long date) {
        this.date = date;
    }

    /**
     * @return value
     */
    public Double getValue() {
        return value;
    }

    /**
     * Sets new value
     *
     * @param value new value of value.
     */
    public void setValue(final Double value) {
        this.value = value;
    }
}
