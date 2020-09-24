package com.example.restservice.model;

/**
 * Event domain object
 */
public class Event extends BaseEntity {

    /**
     * Event content
     */
    private String content;

    /**
     * Event date unix ts
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
     * Gets content.
     *
     * @return value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets new content.
     *
     * @param content new value of content
     */
    public void setContent(final String content) {
        this.content = content;
    }
}
