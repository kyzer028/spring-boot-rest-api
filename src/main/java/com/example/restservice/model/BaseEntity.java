package com.example.restservice.model;

/**
 * Base entity domain object.
 */
public class BaseEntity {
    /**
     * Entity identifier
     */
    private long id;

    /**
     * Gets id.
     *
     * @return value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id new value of id
     */
    public void setId(final long id) {
        this.id = id;
    }
}
