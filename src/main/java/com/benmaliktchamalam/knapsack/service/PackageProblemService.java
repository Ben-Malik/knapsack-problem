package com.benmaliktchamalam.knapsack.service;

import com.benmaliktchamalam.knapsack.constant.APIConstants;
import com.benmaliktchamalam.knapsack.model.Item;
import com.benmaliktchamalam.knapsack.model.Payload;
import com.benmaliktchamalam.knapsack.model.Solution;

import java.util.ArrayList;
import java.util.List;

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
     * <p>
     * Multiplies weight and price values with 100 and puts into separate arrays
     * This is done because the values can be Double and algorithm is designed to
     * work with Integer problems
     * Puts indexes to an array
     * Creates the dynamic programming result matrix for the problem
     *
     * @param payload The payload or problem to be solved.
     * @returns a list of integers as the solution to the problem containing the
     * list of the selected Items
     */
    public Solution solve(Payload payload) {

        var weights = payload.getItems().stream().map(item -> (int) (item.getWeight() * APIConstants.DECIMAL_FACTOR))
                .toArray(Integer[]::new);
        var costs = payload.getItems().stream().map(item -> (int) (item.getCost() * APIConstants.DECIMAL_FACTOR))
                .toArray(Integer[]::new);
        var indexes = payload.getItems().stream().map(Item::getIndex).toArray(Integer[]::new);

        capacity = (int) (payload.getTargetWeight() * APIConstants.DECIMAL_FACTOR);
        complexity = weights.length;

        var resultingMatrix = computeOptimumPackage(weights, costs);
        return createSolution(resultingMatrix, weights, costs, indexes);
    }

    /**
     * A helper method to compute the optimum package given two arrays of weights and costs
     *
     * @param weights The weights as an array.
     * @param costs The costs as an array.
     * @return a 2D optimum package.
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
     *
     * @param resultingMatrix acquired from the weights and costs.
     * @param weights the weights
     * @param costs the costs
     * @param indexes the indexes
     * @return a list of integers as the solution to the package problem.
     */
    private Solution createSolution(int[][] resultingMatrix, Integer[] weights, Integer[] costs,
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
        return new Solution(selectedItems);
    }

}
