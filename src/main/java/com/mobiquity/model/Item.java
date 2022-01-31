package com.mobiquity.model;

/**
 * A class encapsulating the data about items to be added into the package.
 * 
 * @author ben-malik
 */
public class Item {
    
    private Integer index;
    private Double weight;
    private Double cost;
    
    public Item(Integer index, Double weight, Double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }

}
