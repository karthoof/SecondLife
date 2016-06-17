package com.digdes.scrum;

import java.util.Comparator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

/**
 * Created by artemkopytok on 17.06.16.
 */
public class TestMain {
    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        int sum = add(a, b);
        System.out.println(sum);

        Function<Integer, Integer> f = x -> x * 3;

        System.out.println(f.apply(4));

    }

    private static int add(int a, int b) {
        return a + b;
    }
}
