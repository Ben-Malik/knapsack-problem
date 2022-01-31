package com.mobiquity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilities to be used in the test classes.
 * 
 * @author ben-malik
 */
public class TestFileUtilities {
    
    public static Path validFilePath;
    public static Path invalidLinePath;
    public static String invalidTargetWeight;
    public static String invalidItemWeight;
    public static String invalidItemPrice;
    public static String duplicateIndexes;
    public static String invalidNumberItems;

    public static String nonExistingFilePathString;

    public static List<String> validFileLines;
    public static List<String> invalidFileLines;

    static {
        try {
            validFilePath = getResourcePath("fileWithCorrectData");
            invalidLinePath = getResourcePath("fileWithInvalidLineData");
            nonExistingFilePathString = validFilePath.toString() + "fileNotExisting";
            validFileLines = Files.lines(validFilePath).collect(Collectors.toList());
            invalidFileLines = Files.lines(invalidLinePath).collect(Collectors.toList());

            invalidTargetWeight = getResourcePathString("fileWithInvalidTargetWeightData");
            invalidItemWeight = getResourcePathString("fileWithInvalidItemWeightData");
            invalidItemPrice = getResourcePathString("fileWithInvalidItemCostData");
            duplicateIndexes = getResourcePathString("fileWithDuplicateIndexesData");
            invalidNumberItems = getResourcePathString("fileWithInvalidNumberOfItemsData");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * A helper method to grab the resouce path of a file given its file name.
     * @param fileName The name of the file whose resource path is sought.
     * @return The Path of the file name.
     * @throws URISyntaxException in a failure accurrence.
     */
    private static Path getResourcePath(String fileName) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(fileName).toURI());
    }

    /**
     * A helper method to grab the string version path of the given file name.
     * @param fileName the name of the file whose resource path is sought.
     * @return The String of the resource path of the file name .
     * @throws URISyntaxException when any particular failure occurs.
     */
    private static String getResourcePathString(String fileName) throws URISyntaxException {
        return getResourcePath(fileName).toString();
    }
    
}
