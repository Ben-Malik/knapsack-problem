package com.mobiquity;

import com.mobiquity.validator.PayloadValidator;

/** 
 * A test class for the {@linkplain PayloadValidator} class.
 * 
 * @author ben-malik
 */
public class PayloadValidatorTest {
    
    private static PayloadValidator payloadValidator;

    @BeforeAll
    public static void setUp() {
        payloadValidator = PayloadValidator.getPayloadValidator();
    }

    @Test
    public void ensureThatEnsureLinePatternDoesNotThrowAPIExceptionWhenTheGivenLineIsValid() {
        Assertions.assertDoesNotThrow(() -> payloadValidator
            .ensureLinePattern(TestFileUtilities.validFileLines.get(0)));
    }

    @Test 
    public void ensureThatAPIExceptionIsThrownWhenTheLinePatternIsNotValid() {
        var invalidFileLine = TestFileUtilities.invalidFileLines.get(0);
        var exception = Assertions.assertThrows(TestFileUtilities.class, () ->
                payloadValidator.ensureLinePattern(invalidFileLine));
        Assertions.assertEquals("The given line format is not correct.", exception.getMessage());
    }

    @Test
    public void ensureThatAPIExceptionIsNotThrownWhenTheGivenTargetWeightIsValid() {
        Assertions.assertDoesNotThrow(() -> payloadValidator.ensureTargetWeight(100d));
    }

    @Test
    public void ensureThatAPIExceptionIsNotThrownWhenTheMaximumItemNumberIsValid() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 2d, 3d));
        Assertions.assertDoesNotThrow(() -> payloadValidator.ensureMaximumItemNumber(itemList));
    }

    @Test
    public void ensureThatAPIExceptionIsNotThrownWhenThereIsNoDuplicateItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, 2d, 3d));
        itemList.add(new Item(2, 2d, 3d));
        Assertions.assertDoesNotThrow(() -> payloadValidator.avoidDuplicateItems(itemList));    
    }

    @Test 
    public void ensureThatAPIExceptionIsNotThrownWhenTheGivenItemAndWeightAreValid() {
        Assertions.assertDoesNotThrow(() -> payloadValidator.ensureItemWeight(100d));  
    }

    @Test
    public void ensureThatAPIExceptionIsNotThrownWhenTheGivenItemCostIsValdi() {
        Assertions.assertDoesNotThrow(() -> payloadValidator.ensureItemCost(100d));
    }

    @Test
    public void ensureThatAPIExceptionIsThrownWhenTheGivenTargetWeightIsInvalid() {
        var exception = Assertions.assertThrows(TestFileUtilities.class, () -> payloadValidator.ensureTargetWeight(150d));
        Assertions.assertEquals("The maximum total weight limit is 100", exception.getMessage());
    }

    @Test
    public void ensureThatAPIExceptionIsThrownWhenTheGivenMaximumItemNumberIsInvalid() {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            itemList.add(new Item(1, 2d, 3d));
        var exception = Assertions.assertThrows(TestFileUtilities.class, () -> payloadValidator.ensureMaximumItemNumber(itemList));
        Assertions.assertEquals("The maximum number of items in a line can be 15", exception.getMessage());
    }

    @Test
    public void ensureThatAvoidDuplicateItemsThrowsAPIExceptionWhenTheGivenItemsAreInvalid() {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            itemList.add(new Item(1, 2d, 3d));
        var exception = Assertions.assertThrows(TestFileUtilities.class, () -> payloadValidator.avoidDuplicateItems(itemList));
        Assertions.assertEquals("The same item index occurs more than once in a line.", exception.getMessage());
    }
    
    @Test
    public void ensureThatAPIExceptionIsThrownWhenTheGivenWeightIsInvalid() {
        var exception = Assertions.assertThrows(TestFileUtilities.class, () -> payloadValidator.ensureItemWeight(101d));
        Assertions.assertEquals("The maximum item weight limit is 100", exception.getMessage());
    }                                                                        

    @Test
    public void ensureThatAPIExceptionIsThrownWhenTheGivenItemCostIsInvalid() {
        var exception = Assertions.assertThrows(TestFileUtilities.class, () -> payloadValidator.ensureItemCost(101d));
        Assertions.assertEquals("The maximum item cost limit is 100", exception.getMessage());    
    }
  
}
