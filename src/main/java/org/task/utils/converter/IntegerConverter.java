package org.task.utils.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerConverter implements Converter<String, int[]> {

    private static final String DASH = "-";
    private static final String COMMA = ",";

    private int[] convert(String input) {
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("Argument shouldn't be null or empty");
        }
        checkForOddSignInBeginningAndEnd(input, COMMA);
        checkForOddSignInBeginningAndEnd(input, DASH);
        List<Integer> result = new ArrayList<>();
        if (input.contains(COMMA)) {
            String[] numbersString = input.split(COMMA);
            for (String numberString : numbersString) {
                if (numberString.contains(DASH)) {
                    convertRangeAndAddToList(result, numberString);
                } else {
                    result.add(Integer.parseInt(numberString));
                }
            }
        } else if (input.contains(DASH)) {
            convertRangeAndAddToList(result, input);
        } else {
            result.add(Integer.parseInt(input));
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public int[][] convert(String[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Argument shouldn't be null or empty");
        }
        int[][] result = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            result[i] = convert(input[i]);
        }
        return result;
    }

    private void convertRangeAndAddToList(List<Integer> list, String range) {
        String[] rangeArray = range.split(DASH);
        if (rangeArray.length != 2) {
            throw new IllegalArgumentException("The given range consists more, than 2 elements");
        }
        int firstNumber = Integer.parseInt(rangeArray[0]);
        int secondNumber = Integer.parseInt(rangeArray[1]);
        if (firstNumber > secondNumber) {
            throw new IllegalArgumentException("The first number in the given range greater, than the second one");
        }
        list.addAll(IntStream.rangeClosed(firstNumber, secondNumber).boxed().collect(Collectors.toList()));
    }

    private void checkForOddSignInBeginningAndEnd(String string, String sign) {
        if (string.indexOf(sign) == 0 || string.lastIndexOf(sign) == string.length() - 1) {
            throw new IllegalArgumentException("Argument has an odd sign " + sign);
        }
    }

}
