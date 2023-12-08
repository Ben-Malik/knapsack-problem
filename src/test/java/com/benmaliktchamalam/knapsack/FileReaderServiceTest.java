package com.benmaliktchamalam.knapsack;

import com.benmaliktchamalam.knapsack.exception.FileReaderException;
import com.benmaliktchamalam.knapsack.service.FileReaderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

/**
 * A test class for the {@linkplain FileReaderService} class.
 *
 * @author ben-malik
 */
public class FileReaderServiceTest {

    private FileReaderService fileReaderService;

    @BeforeEach
    void setUp() {
        fileReaderService = new FileReaderService();
    }

    @Test
    void ensureThatAPIExceptionIsNotThrownWhenThenGivenDirectoryIsValid() {
        var resultingLines = fileReaderService.readLines(TestFileUtilities.validFilePath.toString()).collect(Collectors.toList());
        Assertions.assertEquals(4, resultingLines.size());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenTheDirectoryIsInvalid() {
        var exception = Assertions.assertThrows(FileReaderException.class, () ->
                fileReaderService.readLines(TestFileUtilities.nonExistingFilePathString));
        Assertions.assertEquals("An exception occurred while reading the file.", exception.getMessage());
    }
}
