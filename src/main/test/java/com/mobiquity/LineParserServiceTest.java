package com.mobiquity;

import com.mobiquity.exception.InputFormatException;
import com.mobiquity.service.LineParserService;
import com.mobiquity.validator.PayloadValidator;
import com.mobiquity.TestFileUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * A test class for the {@linkplain LineParserService} class.
 * 
 * @author ben-malik
 */
public class LineParserServiceTest {
    
    private static final String TEST_EXCEPTION = "test";
    private static final String validFileLine = TestFileUtilities.validFileLines.get(0);
    private static PayloadValidator payloadValidator;
    private LineParserService lineParserService;

    @BeforeAll
    static void init() {
        payloadValidator = Mockito.mock(PayloadValidator.class);
    }

    @BeforeEach
    void setUp() {
        lineParserService = new LineParserService();
        lineParserService.payloadValidator = payloadValidator;
    }

    @Test
    void ensureAPIExceptionIsNotThrownWhenTheGivenLineIsValid() {
        var validLines = Arrays.stream(validFileLine.split(":")).map(String::trim).toArray(String[]::new);
        doReturn(new String[]{validLines[0], validLines[1]}).when(payloadValidator).checkLineRegex(Mockito.anyString());
        var payload = lineParserService.parse(validFileLine);
        Assertions.assertEquals(81d, payload.getTargetWeight());
        Assertions.assertEquals(30.18, payload.getItems().get(0).getWeight());
        Assertions.assertEquals(6, payload.getItems().size());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenThePayloadValidatorThrowsAnExceptionDuringLinePatternEnsurance() {
        doThrow(new InputFormatException(TEST_EXCEPTION)).when(payloadValidator).ensure(Mockito.anyString());
        var exception = Assertions.assertThrows(InputFormatException.class, () -> lineParser.parseLine(validFileLine));
        Assertions.assertEquals(TEST_EXCEPTION, exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenThePayloadValidatorThrowsAnExceptionWhileEnsuringTheTargetWeight() {
        doThrow(new InputFormatException(TEST_EXCEPTION)).when(payloadValidator).ensureTargetWeight(Mockito.anyDouble());
        var exception = Assertions.assertThrows(InputFormatException.class, () -> lineParser.parseLine(validFileLine));
        Assertions.assertEquals(TEST_EXCEPTION, exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenThePayloadValidatorThrowsAnExceptionWhileEnsuringTheMaximumItemNumber() {
        doThrow(new InputFormatException(TEST_EXCEPTION)).when(payloadValidator).ensureMaximumItemNumber(Mockito.anyList());
        var exception = Assertions.assertThrows(InputFormatException.class, () -> lineParser.parseLine(validFileLine));
        Assertions.assertEquals(TEST_EXCEPTION, exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenThePayloadValidatorThrowsAnExceptionWhileAvoidingDuplicateItems() {
        doThrow(new InputFormatException(TEST_EXCEPTION)).when(payloadValidator).avoidDuplicateItems(Mockito.anyList());
        var exception = Assertions.assertThrows(InputFormatException.class, () -> lineParser.parseLine(validFileLine));
        Assertions.assertEquals(TEST_EXCEPTION, exception.getMessage());
    }
    
}
