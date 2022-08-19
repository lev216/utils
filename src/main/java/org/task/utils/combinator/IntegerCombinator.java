package org.task.utils.combinator;

import java.util.ArrayList;
import java.util.List;

public class IntegerCombinator implements Combinator<int[][]> {

    //works with not big amount of arrays.
    //recursion is needed for this case, but I don't have time to do it :)
    @Override
    public int[][] combine(int[][] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Argument shouldn't be null or empty");
        }
        if (input.length == 1) {
            return input;
        }
        List<int[]> result = new ArrayList<>();
        int xCursor = input.length - 1;
        int yCursor = 0;
        int xAnchor = xCursor - 1;
        int yAnchor = 0;
        while (xAnchor >= 0) {
            int[] combination = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                if (i == xAnchor) {
                    combination[i] = input[xAnchor][yAnchor];
                } else if (i == xCursor) {
                    combination[i] = input[xCursor][yCursor];
                    yCursor++;
                } else {
                    combination[i] = input[i][0];
                }
            }
            if (!isDuplicated(result, combination)) {
                result.add(combination);
            }
            if (yCursor == input[xCursor].length) {
                yCursor = 0;
                xCursor--;
            }
            if (xCursor == xAnchor) {
                yAnchor++;
                xCursor = input.length - 1;
            }
            if (yAnchor == input[xAnchor].length) {
                yAnchor = 0;
                xAnchor--;
            }
        }
        return convertToArray(result);
    }

    private int[][] convertToArray(List<int[]> input) {
        int[][] result = new int[input.size()][];
        int i = 0;
        for (int[] array : input) {
            result[i] = array;
            i++;
        }
        return result;
    }

    private boolean isDuplicated(List<int[]> list, int[] array) {
        for (int[] i : list) {
            boolean duplicated = true;
            for (int j = 0; j < i.length; j++) {
                duplicated = duplicated && i[j] == array[j];
            }
            if (duplicated) {
                return true;
            }
        }
        return false;
    }
}
