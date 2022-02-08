package com.mobiquity.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mobiquity.constant.Constants;
import com.mobiquity.exception.InputFormatException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Payload;
import com.mobiquity.validator.PayloadValidator;

/**
 * A service class dealing with the line parsing and validity.
 * 
 * @author ben-malik
 */
public class LineParserService {
    
    public PayloadValidator payloadValidator = PayloadValidator.getPayloadValidator();

    /**
     * Parses and validate a given line of the file into a {@linkplain Payload}
     * @param line The line to parsed and validated.
     * @throws an {@linkplain InputFormatException}
     * @return an object of {@linkplain Payload} of the given string of line(pacakge).
     */
    public Payload parse(String line) throws InputFormatException {
        String[] problemParts = payloadValidator.ensureLinePattern(line);
        var targetWeight = parseTargetWeight(problemParts[0]);
        var items = parseItems(problemParts[1]);
        
        return new Payload(targetWeight, items);
    }

    /**
     * A helper method to parse and check the validity of the given target weight value.
     * @param targetWeightValue The target weight value to be parsed.
     * @return the Double version of the parsed string of target weight.
     */
    private Double parseTargetWeight(String targetWeightValue) {
        var targetWeight = Double.parseDouble(targetWeightValue);
        payloadValidator.ensureTargetWeight(targetWeight);

        return targetWeight;
    }

    /**
     * Parses the given string of items into a list of {@linkplain Item}s.
     * @param items The string value of the items to be parsed.
     * @return a list of {@linkplain Item}s parsed.
     */
    private List<Item> parseItems(String items) {
        var itemValues = items.split(Constants.ITEMS_SEPERATOR);

        ItemParserService itemParserService = new ItemParserService();

        var parsedItems = Arrays.stream(itemValues).map(itemParserService::parse)
                .sorted(Comparator.comparing(Item::getWeight))
                .collect(Collectors.toList());

        payloadValidator.ensureMaximumItemNumber(parsedItems);
        payloadValidator.avoidDuplicateItems(parsedItems);

        return parsedItems;
    }

}
