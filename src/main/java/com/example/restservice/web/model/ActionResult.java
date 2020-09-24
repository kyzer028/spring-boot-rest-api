package com.example.restservice.web.model;

/**
 * Action result API model object
 */
public class ActionResult {
    /**
     * Action name
     */
    private String name;
    /**
     * Action execution order
     */
    private int order;

    /**
     * Constructor with parameters
     *
     * @param name  action name
     * @param order execution order
     */
    public ActionResult(final String name, final int order) {
        this.name = name;
        this.order = order;
    }

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

    /**
     * Gets order.
     *
     * @return value of order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets new order.
     *
     * @param order new value of order
     */
    public void setOrder(final int order) {
        this.order = order;
    }
}
