package com.benmaliktchamalam.service;

import com.benmaliktchamalam.constant.Constants;
import com.benmaliktchamalam.exception.InputFormatException;
import com.benmaliktchamalam.model.Item;
import com.benmaliktchamalam.validator.PayloadValidator;

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
     * @param itemValue The string version of theitem value to be parsed.
     * @return The Object version of the given string.
     * @throws {@linkplain InputFormatException} in case any of the validations does not pass.
     */
    public Item parse(String itemValue) throws InputFormatException {
        var item = itemValue.replaceAll(Constants.REMOVED_PARTS_REGEX, Constants.REPLACEMENT_REGEX).split(Constants.PROPERTIES_SEPARATOR);

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
