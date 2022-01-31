package com.mobiquity.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mobiquity.constant.Constants;
import com.mobiquity.exception.InputFormatException;
import com.mobiquity.model.Item;

/**
 * A valicator class to ensure all the constraints on the Payload or Package to be sent.
 * 
 * @author ben-malik
 */
public class PayloadValidator {

    private static PayloadValidator payloadValidator;

    public static PayloadValidator getPayloadValidator() {
        if (payloadValidator == null) {
            payloadValidator = new PayloadValidator();
        }
        return payloadValidator;
    }

    /**
     * 
     * @param line
     * @return
     */
    public String[] ensureLinePattern(String line) {

        if (Constants.PROBLEM_PARTS_SEPARATOR_PATTERN.asPredicate().test(line)) {
            var parts = line.split(":");
            parts[0] = parts[0].trim();
            parts[1] = parts[1].trim();
            if (Constants.TWO_DECIMAL_DOUBLE_PATTERN.asPredicate().test(parts[0])
                    && Constants.ITEM_PART_PATTERN.asPredicate().test(parts[1] + Constants.SINGLE_SPACE))
                return parts;
        }
        throw new InputFormatException(Constants.LINE_INCOMPATIBLE_ERROR);
    }

    /**
     * 
     * @param targetWeight
     */
    public void ensureTargetWeight(Double targetWeight) {
        if (targetWeight > Double.parseDouble(Constants.MAXIMUM_PACKAGE_WEIGHT)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_TOTAL_WEIGHT_LIMIT_ERROR, Constants.MAXIMUM_PACKAGE_WEIGHT));
        }
    }

    /**
     * 
     * @param items
     */
    public void ensureMaximumItemNumber(List<Item> items) {
        if (items.size() > Integer.parseInt(Constants.MAXIMUM_ITEM_NUMBER)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_ITEM_NUMBER_ERROR, Constants.MAXIMUM_ITEM_NUMBER));
        }
    }

    /**
     * 
     * @param weight
     */
    public void ensureItemWeight(Double weight) {

        if (weight > Double.parseDouble(Constants.MAXIMUM_ITEM_WEIGHT)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_ITEM_WEIGHT_LIMIT_ERROR, Constants.MAXIMUM_ITEM_WEIGHT));
        }
    }

    /**
     * 
     * @param cost
     */
    public void ensureItemCost(Double cost) {
        if (cost > Double.parseDouble(Constants.MAXIMUM_ITEM_COST)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_ITEM_PRICE_LIMIT_ERROR, Constants.MAXIMUM_ITEM_COST));
        }
    }

    /**
     * 
     * @param items
     */
    public void avoidDuplicateItems(List<Item> items) {
        Set<Integer> duplicateItems = new HashSet<>();
        if (!items.stream().map(Item::getIndex).filter(n -> !duplicateItems.add(n)).collect(Collectors.toSet()).isEmpty()) {
            throw new InputFormatException(Constants.DUPLICATE_ITEM_INDEX_ERROR);
        }
    }

}
