package com.mobiquity.model;

import java.util.List;

/**
 * An encapsulation of a the package.
 *
 * @author ben-malik
 */
public class Payload {

    private Double targetWeight;
    private List<Item> items;

    public Payload(Double targetWeight, List<Item> items) {
        this.targetWeight = targetWeight;
        this.items = items;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public List<Item> getItems() {
        return items;
    }

}
