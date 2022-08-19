package org.task.utils.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.task.utils.TestUtils;

class IntegerConverterTest {

    private final Converter<String, int[]> converter = new IntegerConverter();

    @Test
    void convertEmptyArraysTest() {
        assertThrows(IllegalArgumentException.class, () -> converter.convert(null));
        assertThrows(IllegalArgumentException.class, () -> converter.convert(new String[0]));
    }

    @Test
    void convertIncorrectArrayTest() {
        String[] emptyStringInArray = {"1", "3-5", "", "1,2-4"};
        String[] oddCommaInArray = {"1", "3-5", "1,2,", "1,2-4"};
        String[] oddDashInArray = {"1", "3-5", "1,2", "1,2-4-6"};
        String[] firstNumberLessInRangeInArray = {"1", "5-3", "1,2", "1,2-4"};
        String[] notNumberInArray = {"1", "d,2", "1,2-4"};
        assertThrows(IllegalArgumentException.class, () -> converter.convert(emptyStringInArray));
        assertThrows(IllegalArgumentException.class, () -> converter.convert(oddCommaInArray));
        assertThrows(IllegalArgumentException.class, () -> converter.convert(oddDashInArray));
        assertThrows(IllegalArgumentException.class, () -> converter.convert(firstNumberLessInRangeInArray));
        assertThrows(NumberFormatException.class, () -> converter.convert(notNumberInArray));
    }

    @Test
    void convertCorrectArrayTest() {
        String[] oneNumberInArray = {"1"};
        String[] oneRangeInArray = {"1-5"};
        String[] differentNumbersWithCommaInArray = {"1,5", "4,6,20"};
        String[] differentRangesInArray = {"1-5", "4-10"};
        String[] mixedArray = {"1,2", "5", "5,7-10,4", "1-2,5,7-11"};

        int[][] oneNumberInArrayResult = converter.convert(oneNumberInArray);
        int[][] oneRangeInArrayResult = converter.convert(oneRangeInArray);
        int[][] differentNumbersWithCommaInArrayResult = converter.convert(differentNumbersWithCommaInArray);
        int[][] differentRangesInArrayResult = converter.convert(differentRangesInArray);
        int[][] mixedArrayResult = converter.convert(mixedArray);

        TestUtils.assertEqualArrays(new int[][]{{1}}, oneNumberInArrayResult);
        TestUtils.assertEqualArrays(new int[][]{{1, 2, 3, 4, 5}}, oneRangeInArrayResult);
        TestUtils.assertEqualArrays(new int[][]{{1, 5}, {4, 6, 20}}, differentNumbersWithCommaInArrayResult);
        TestUtils.assertEqualArrays(new int[][]{{1, 2, 3, 4, 5}, {4, 5, 6, 7, 8, 9, 10}}, differentRangesInArrayResult);
        TestUtils.assertEqualArrays(new int[][]{{1, 2}, {5}, {5, 7, 8, 9, 10, 4}, {1, 2, 5, 7, 8, 9, 10, 11}}, mixedArrayResult);
    }

}