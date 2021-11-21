package com.leetcode;

/*
https://leetcode.com/problems/fizz-buzz/
Easy.

Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number
and for the multiples of five output “Buzz”.
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
*/

import java.util.ArrayList;
import java.util.List;

class _412_FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        if (n <= 0) return new ArrayList<>();

        final ArrayList<String> res = new ArrayList<>(n);

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                res.add("FizzBuzz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    public static List<String> fizzBuzz2(int n) {
        if (n <= 0) return new ArrayList<>();

        final ArrayList<String> res = new ArrayList<>(n);
        final StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            boolean divisibleBy3 = (i % 3 == 0);
            boolean divisibleBy5 = (i % 5 == 0);
            if (divisibleBy3) sb.append("Fizz");
            if (divisibleBy5) sb.append("Buzz");
            if (sb.length() == 0) sb.append(i);
            res.add(sb.toString());
            // StringBuilder.delete() delete from [start, end)
            sb.delete(0, sb.length());
        }

        return res;
    }
}
