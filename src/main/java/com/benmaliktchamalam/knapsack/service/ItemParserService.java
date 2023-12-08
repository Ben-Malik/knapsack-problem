package com.benmaliktchamalam.knapsack.service;

import com.benmaliktchamalam.knapsack.constant.APIConstants;
import com.benmaliktchamalam.knapsack.exception.InputFormatException;
import com.benmaliktchamalam.knapsack.validator.PayloadValidator;
import com.benmaliktchamalam.knapsack.model.Item;

/**
 * A class for dealing with the item checks and parsing.
 *
 * @author ben-malik
 */
public class ItemParserService {

    public PayloadValidator payloadValidator = PayloadValidator.getPayloadValidator();

    /**
     * Parses a given String of item value into an {@linkplain Item} object.
     *
     * @param itemValue The string version of the item value to be parsed.
     * @return The Object version of the given string.
     */
    public Item parse(String itemValue) throws InputFormatException {
        var item = itemValue.replaceAll(APIConstants.REMOVED_PARTS_REGEX, APIConstants.REPLACEMENT_REGEX).split(APIConstants.PROPERTIES_SEPARATOR);

        var index = parseIndex(item[0]);
        var weight = parseWeight(item[1]);
        var cost = parseCost(item[2]);

        return new Item(index, weight, cost);
    }

    /**
     * A helper method to parses a given string index into an integer.
     *
     * @param index The string index to be parsed.
     * @return The integer version of the given index.
     */
    private Integer parseIndex(String index) {
        return Integer.parseInt(index);
    }

    /**
     * A helper method to parses and validates the given weight.
     *
     * @param weight The weight to be parsed and validated.
     * @return The Double version of the given weight.
     */
    private Double parseWeight(String weight) {
        var parsedWeight = Double.parseDouble(weight);
        payloadValidator.ensureItemWeight(parsedWeight);
        return parsedWeight;
    }

    /**
     * A helper method to parse and validate the given cost as String into a Double.
     *
     * @param cost The cost to be parsed.
     * @return The newly parsed cost.
     */
    private Double parseCost(String cost) {
        var parsedCost = Double.parseDouble(cost);
        payloadValidator.ensureItemCost(parsedCost);
        return parsedCost;
    }

}
