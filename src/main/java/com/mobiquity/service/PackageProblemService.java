package com.mobiquity.service;

import java.util.ArrayList;
import java.util.List;

import com.mobiquity.constant.Constants;
import com.mobiquity.model.Item;
import com.mobiquity.model.Payload;

/**
 * The service class to solve the package problem.
 * 
 * @author ben-malik
 */
public class PackageProblemService {

    private int capacity;
    private int complexity;

    /**
     * Conducts all computations and finds the most optimum solution to the package problem.
     * 
     * Multiplies weight and price values with 100 and puts into separate arrays
     * This is done because the values can be Double and algorithm is designed to
     * work with Integer problems
     * Puts indexes to an array
     * Creates the dynamic programming result matrix for the problem
     *
     * @param payload The payload or problem to be solved.
     * @returns a list of integers as the solution to the problem containing the
     *          list of the selected Items
     */
    public List<Integer> solve(Payload payload) {

        var weights = payload.getItems().stream().map(item -> (int) (item.getWeight() * Constants.DECIMAL_FACTOR))
                .toArray(Integer[]::new);
        var costs = payload.getItems().stream().map(item -> (int) (item.getCost() * Constants.DECIMAL_FACTOR))
                .toArray(Integer[]::new);
        var indexes = payload.getItems().stream().map(Item::getIndex).toArray(Integer[]::new);

        capacity = (int) (payload.getTargetWeight() * Constants.DECIMAL_FACTOR);
        complexity = weights.length;

        var resultingMatrix = computeOptimumPackage(weights, costs);
        return createSolution(resultingMatrix, weights, costs, indexes);
    }

    /**
     * A helper method to compute the optimum package given two arrays of weights and costs
     * @param weights
     * @param costs
     * @return
     */
    private int[][] computeOptimumPackage(Integer[] weights, Integer[] costs) {

        var resultingMatrix = new int[complexity + 1][capacity + 1];

        for (var item = 1; item <= complexity; item++) {
            for (var weight = 0; weight <= capacity; weight++) {
                int currentWeight = weights[item - 1];
                if (currentWeight <= weight) {
                    resultingMatrix[item][weight] = Math.max(
                            costs[item - 1] + resultingMatrix[item - 1][weight - currentWeight],
                            resultingMatrix[item - 1][weight]);
                } else {
                    resultingMatrix[item][weight] = resultingMatrix[item - 1][weight];
                }
            }
        }
        return resultingMatrix;
    }

    /**
     * Creates a solution to the problem given the resulting matrix, weights and costs.
     * @param resultingMatrix acquired from the weights and costs.
     * @param weights 
     * @param costs
     * @param indexes
     * @return a list of integers as the solution to the package problem.
     */
    private List<Integer> createSolution(int[][] resultingMatrix, Integer[] weights, Integer[] costs,
            Integer[] indexes) {

        List<Integer> selectedItems = new ArrayList<>();
        int bestPrice = resultingMatrix[complexity][capacity];

        for (int i = complexity - 1; i >= 0 && bestPrice > 0; i--) {
            if (bestPrice != resultingMatrix[i][capacity]) {
                selectedItems.add(indexes[i]);
                bestPrice = bestPrice - costs[i];
                capacity = capacity - weights[i];
            }
        }
        return selectedItems;
    }

}
