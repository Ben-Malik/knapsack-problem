package com.temizcode.constant;

import java.util.regex.Pattern;

/**
 * A final class for all the constants usable all over the entire app.
 *
 * @author ben-malik
 */
public final class Constants {

    // Regular expressions.
    public static final String PROPERTY_SEPERATOR = ",";
    public static final String ITEMS_SEPERATOR = " ";

    // Constraints
    public static final String MAXIMUM_PACKAGE_WEIGHT = "100";
    public static final String MAXIMUM_ITEM_NUMBER = "15";
    public static final String MAXIMUM_ITEM_COST = "100";
    public static final String MAXIMUM_ITEM_WEIGHT = "100";

    public static final int DECIMAL_FACTOR = 100;

    public static final String READ_FILE_EXCEPTION_MESSAGE = "An exception occurred while reading the file.";
    public static final String REPLACEMENT_REGEX = "";
    public static final String REMOVED_PARTS_REGEX = "[()\\€]";
    public static final String PROPERTIES_SEPARATOR = ",";

    //String Operations Constants
    public static final Pattern PROBLEM_PARTS_SEPARATOR_PATTERN = Pattern.compile("[ ]:[ ]");
    public static final String TWO_DECIMAL_DOUBLE_REGEX = "(\\d*(.\\d{1,2})?)";
    public static final Pattern TWO_DECIMAL_DOUBLE_PATTERN = Pattern.compile("^" + TWO_DECIMAL_DOUBLE_REGEX + "$");
    public static final String ITEM_PART_REGEX = "^((\\(\\d*," + TWO_DECIMAL_DOUBLE_REGEX + ",\\€" + TWO_DECIMAL_DOUBLE_REGEX + "\\)[ ])*)$";
    public static final Pattern ITEM_PART_PATTERN = Pattern.compile(ITEM_PART_REGEX);
    public static final String DELIMITER = "";
    public static final String SINGLE_SPACE = " ";

    //Exceptional Messages
    public static final String LINE_INCOMPATIBLE_ERROR = "The given line format is not accurrate.";
    public static final String MAX_TOTAL_WEIGHT_LIMIT_ERROR = "The maximum total weight limit is ";
    public static final String MAX_ITEM_NUMBER_ERROR = "The maximum number of items in a line can be ";
    public static final String DUPLICATE_ITEM_INDEX_ERROR = "The same item index occurs more than once in a line.";
    public static final String MAX_ITEM_WEIGHT_LIMIT_ERROR = "The maximum item weight limit is ";
    public static final String MAX_ITEM_PRICE_LIMIT_ERROR = "The maximum item cost limit is ";

}