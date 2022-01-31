package com.mobiquity;

import com.mobiquity.TestFileUtilities;
import com.mobiquity.service.ItemParserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.doThrow;
/**
 * A test class for the {@linkplain ItemParserService} class.
 * 
 * @author ben-malik
 */
public class ItemParserServiceTest {

    private static final String TEST_EXCEPTION = "test";
    private static final List<String> validFileLines = InputFileInformation.validFileLines;
    private static ProblemValidator testPayloadValidator;
    private ItemParserService itemParserService;
    private String validItem;

    @BeforeAll
    static void init() {
        testPayloadValidator = Mockito.mock(PayloadValidator.class);
    }

    @BeforeEach
    void setUp() {
        itemParserService = new ItemParserService();
        itemParserService.payloadValidator = testPayloadValidator;
        validItem = validFileLines.get(0).split(" : ")[1].split(" ")[0];
    }

    @Test
    void ensureThatAPIExceptionIsNotThrownWhenTheGivenItemIsValid() {
        var parsedItem = itemParserService.parse(validItem);
        Assertions.assertEquals(1, parsedItem.getIndex());
        Assertions.assertEquals(53.38, parsedItem.getWeight());
        Assertions.assertEquals(45, parsedItem.getCost());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenThePayloadValidatorThrowsException() {
        doThrow(new InputFormatException(TEST_EXCEPTION)).when(testPayloadValidator).ensureItemWeight(Mockito.anyDouble());
        var exception = Assertions.assertThrows(InputFormatException.class, () -> itemParserService.parse(validItem));
        Assertions.assertEquals(TEST_EXCEPTION, exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenEnsureItemCostThrowsExceptionAtPayloadValidation() {
        doThrow(new InputFormatException(TEST_EXCEPTION)).when(testPayloadValidator).ensureItemCost(Mockito.anyDouble());
        var exception = Assertions.assertThrows(InputFormatException.class, () -> itemParserService.parse(validItem));
        Assertions.assertEquals(TEST_EXCEPTION, exception.getMessage());
    }
    
}
