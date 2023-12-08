package com.benmaliktchamalam.knapsack;

import com.benmaliktchamalam.knapsack.model.Item;
import com.benmaliktchamalam.knapsack.model.Payload;
import com.benmaliktchamalam.knapsack.service.PackageProblemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

        var payload = new Payload(10d, Arrays.asList(items.clone()));
        var solution = packageProblemService.solve(payload);
        Assertions.assertEquals("1,5", solution.toStringAndSort());
    }

    @Test
    void ensureSolveReturnsZeroWhenTheGivenPayloadHasItemsWithZeros() {
        Item[] itemsArray = {new Item(1, 0d, 0d),
                new Item(4, 0d, 0d),
                new Item(5, 0d, 0d)};

        var payload = new Payload(10d, Arrays.asList(itemsArray.clone()));
        var solution = packageProblemService.solve(payload);
        Assertions.assertEquals("-", solution.toStringAndSort());
    }

    @Test
    void ensureThatSolveReturnsOnlyItemsWithZeroWeightWhenTheGivenTargetWeightIsZero() {
        Item[] itemsArray = {new Item(1, 8d, 5d),
                new Item(4, 0d, 3d),
                new Item(5, 0d, 2d)};

        var payload = new Payload(0d, Arrays.asList(itemsArray.clone()));
        var solution = packageProblemService.solve(payload);
        Assertions.assertEquals("4,5", solution.toStringAndSort());
    }

}
