package org.task.utils.converter;

public interface Converter<T, R> {

    /**
     * Converts one array into another
     * @param input - given array
     * @return result
     */
    R[] convert(T[] input);
}
