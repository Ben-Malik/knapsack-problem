package com.mobiquity;

import com.mobiquity.mock.InputFileInformation;
import com.mobiquity.service.PackageProblemService;
import com.mobiquity.validator.PayloadValidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A test class for the {@linkplain PackageProblemService} class.
 * 
 * @author ben-malik
 */
public class PackageProblemServiceTest {
    
    private PackageProblemService packageProblemService;

    @BeforeEach
    void setUp() {
        packageProblemService = new PackageProblemService();
    }

    @Test
    void ensureThatSolveReturnsACorrectSolutionWhenTheGivenPayloadIsValid() {
        Item[] items = {new Item(1, 2d, 3d),
                new Item(4, 5d, 7d),
                new Item(5, 6d, 8d)};

        var Payload = new Payload(10d, Arrays.asList(itemsArray.clone()));
        var solution = problemSolver.solve(problem);
        Assertions.assertEquals("1,5", solution.toString());
    }

    @Test
    void ensureSoveReturnsZeroWhenTheGivenPayloadHasItemsWithZeros() {
        Item[] itemsArray = {new Item(1, 0d, 0d),
                new Item(4, 0d, 0d),
                new Item(5, 0d, 0d)};

        var problem = new Problem(10d, Arrays.asList(itemsArray.clone()));
        var solution = problemSolver.solve(problem);
        Assertions.assertEquals("-", solution.toString());
    }

    @Test
    void ensureThatSolveReturnsOnlyItemsWithZeroWeightWhenTheGivenTargetWeightIsZero() {
        Item[] itemsArray = {new Item(1, 8d, 5d),
                new Item(4, 0d, 3d),
                new Item(5, 0d, 2d)};

        var problem = new Problem(0d, Arrays.asList(itemsArray.clone()));
        var solution = problemSolver.solve(problem);
        Assertions.assertEquals("4,5", solution.toString());
    }
   
}
