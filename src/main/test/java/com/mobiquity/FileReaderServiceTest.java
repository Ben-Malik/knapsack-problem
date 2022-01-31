package com.mobiquity.reader;

import com.mobiquity.exception.FileReadException;
import com.mobiquity.mock.InputFileInformation;
import com.mobiquity.reader.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import com.mobiquity.service.FileReaderService;
import com.mobiquity.service.PackageProblemService;

/**
 * A test class for the {@linkplain FileReaderService} class.
 * 
 * @author ben-malik
 */
public class FileReaderServiceTest {
    
    private PackageProblemService packageProblemService;
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
        var exception = Assertions.assertThrows(FileReadException.class, () ->
                fileReaderService.readLines(TestFileUtilities.nonExistingFilePathString));
        Assertions.assertEquals("An exception occurred while reading file.", exception.getMessage());
    }
}
