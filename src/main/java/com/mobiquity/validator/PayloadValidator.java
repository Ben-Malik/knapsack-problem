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
     * Checks to ensure the pattern validity of the given line 
     * @param line The line whose pattern validity is to be checked.
     * @return An array of strings.
     * @throws InputFormatException in case of any particular failure.
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
     * Checks to ensure the validity of the given target weight.
     * @param targetWeight The target weight whose validity is sought.
     * @throws InputFormatException in case of a failure.
     */
    public void ensureTargetWeight(Double targetWeight) {
        if (targetWeight > Double.parseDouble(Constants.MAXIMUM_PACKAGE_WEIGHT)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_TOTAL_WEIGHT_LIMIT_ERROR, Constants.MAXIMUM_PACKAGE_WEIGHT));
        }
    }

    /**
     * Checks for the validity of the maximum item number of a list of items
     * @param items The items whose validity is to be checked.
     * @throws InputFormatException in case of a failure.
     */
    public void ensureMaximumItemNumber(List<Item> items) {
        if (items.size() > Integer.parseInt(Constants.MAXIMUM_ITEM_NUMBER)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_ITEM_NUMBER_ERROR, Constants.MAXIMUM_ITEM_NUMBER));
        }
    }

    /**
     * Checks to ensure the validity of the item's weight
     * @param weight The weight whose validity is sought.
     * @throws InputFormatException in case of a failure.
     */
    public void ensureItemWeight(Double weight) {

        if (weight > Double.parseDouble(Constants.MAXIMUM_ITEM_WEIGHT)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_ITEM_WEIGHT_LIMIT_ERROR, Constants.MAXIMUM_ITEM_WEIGHT));
        }
    }

    /**
     * Checks to ensure the validity of a cost
     * @param cost The cost whose validity is sought.
     * @throws InputFormatException in case of a failure.
     */
    public void ensureItemCost(Double cost) {
        if (cost > Double.parseDouble(Constants.MAXIMUM_ITEM_COST)) {
            throw new InputFormatException(String.join(Constants.DELIMITER, Constants.MAX_ITEM_PRICE_LIMIT_ERROR, Constants.MAXIMUM_ITEM_COST));
        }
    }

    /**
     * Checks to ensure there are no duplicate items in a given list of items
     * @param items The list of items to be checked.
     * @throws InputFormatException in case of a failure.
     */
    public void avoidDuplicateItems(List<Item> items) {
        Set<Integer> duplicateItems = new HashSet<>();
        if (!items.stream().map(Item::getIndex).filter(n -> !duplicateItems.add(n)).collect(Collectors.toSet()).isEmpty()) {
            throw new InputFormatException(Constants.DUPLICATE_ITEM_INDEX_ERROR);
        }
    }

}
