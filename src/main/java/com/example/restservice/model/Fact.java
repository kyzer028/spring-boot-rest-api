package com.example.restservice.model;

/**
 * Fact domain object
 */
public class Fact extends BaseEntity {
    /**
     * Fact name
     */
    private String name;

    /**
     * Gets name.
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name new value of name
     */
    public void setName(final String name) {
        this.name = name;
    }
}
