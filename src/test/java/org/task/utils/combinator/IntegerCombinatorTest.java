package org.task.utils.combinator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.task.utils.TestUtils;

class IntegerCombinatorTest {

    private final Combinator<int[][]> combinator = new IntegerCombinator();

    @Test
    void combineEmptyOrNullTest() {
        assertThrows(IllegalArgumentException.class, () -> combinator.combine(null));
        assertThrows(IllegalArgumentException.class, () -> combinator.combine(new int[0][]));
    }

    @Test
    void combineTest() {
        int[][] singleNumberArray = {{1}};
        int[][] oneNumberArray = {{1}, {2}, {3}};
        int[][] differentNumbersArray = {{1, 3, 4, 5}, {2}, {3, 4}};

        int[][] singleNumberArrayResult = {{1}};
        int[][] oneNumberArrayResult = {{1, 2, 3}};
        int[][] differentNumbersArrayResult = {{1, 2, 3}, {1, 2, 4}, {3, 2, 3}, {3, 2, 4}, {4, 2, 3}, {4, 2, 4},
                {5, 2, 3}, {5, 2, 4}};

        TestUtils.assertEqualArrays(singleNumberArrayResult, combinator.combine(singleNumberArray));
        TestUtils.assertEqualArrays(oneNumberArrayResult, combinator.combine(oneNumberArray));
        TestUtils.assertEqualArrays(differentNumbersArrayResult, combinator.combine(differentNumbersArray));
    }
}