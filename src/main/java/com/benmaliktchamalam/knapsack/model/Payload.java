package com.benmaliktchamalam.knapsack.model;

import java.util.List;

/**
 * An encapsulation of a the package.
 *
 * @author ben-malik
 */
public class Payload {

    private final Double targetWeight;
    private final List<Item> items;

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
