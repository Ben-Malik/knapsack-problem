package com.mobiquity;


import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * A test class for the {@linkplain Packer} class method's scenrios.
 *
 * @author ben-malik
 */
public class PackerTest {

    @Test
    public void testPackWhenGivenInputIsValid() throws APIException {
        String allSolutions = Packer.pack(TestFileUtilities.validFilePath.toString());
        String[] solutions = allSolutions.split("\n");
        int result = 1;
        String sol = String.valueOf(result);
        Assertions.assertEquals(4, solutions.length);
        Assertions.assertEquals("4", solutions[0]);
        Assertions.assertEquals("-", solutions[1]);
        Assertions.assertEquals("2,7", solutions[2]);
        Assertions.assertEquals("8,9", solutions[3]);
    }

    @Test
    public void ensureThatAPIExceptionIsThrownWhenTheGivenFilePathIsInvalid() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.nonExistingFilePathString));
        Assertions.assertEquals("An exception occurred while reading the file.", exception.getMessage());

    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenTheGivenLineInputIsInvalid() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.invalidLinePath.toString()));
        Assertions.assertEquals("The given line format is not accurrate.", exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenTheGivenMaximumIsExceeded() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.invalidTargetWeight));
        Assertions.assertEquals("The maximum total weight limit is 100", exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenThereIsDuplicateIndex() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.duplicateIndexes));
        Assertions.assertEquals("The same item index occurs more than once in a line.", exception.getMessage());
    }

    @Test
    void ensureThatAPIExceptionIsThrownWhenTheGivenMaximumItemIsExceeded() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.invalidItemWeight));
        Assertions.assertEquals("The maximum item weight limit is 100", exception.getMessage());
    }

    @Test
    void ensureAPIExceptionIsThrownWhenTheMaximumCostIsExceeded() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.invalidItemPrice));
        Assertions.assertEquals("The maximum item cost limit is 100", exception.getMessage());
    }

    @Test
    void ensureAPIExceptionIsThrownWhenTheMaximumItemNumberIsExceeded() {
        var exception = Assertions.assertThrows(APIException.class, () -> Packer.pack(TestFileUtilities.invalidNumberItems));
        Assertions.assertEquals("The maximum number of items in a line can be 15", exception.getMessage());
    }
}
