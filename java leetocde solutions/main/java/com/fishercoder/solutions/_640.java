package com.fishercoder.solutions;

/**
 * 640. Solve the Equation
 *
 * Solve a given equation and return the value of x in the form of string "x=#value".
 * The equation contains only '+', '-' operation, the variable x and its coefficient.

 If there is no solution for the equation, return "No solution".

 If there are infinite solutions for the equation, return "Infinite solutions".

 If there is exactly one solution for the equation, we ensure that the value of x is an integer.

 Example 1:
 Input: "x+5-3+x=6+x-2"
 Output: "x=2"

 Example 2:
 Input: "x=x"
 Output: "Infinite solutions"

 Example 3:
 Input: "2x=x"
 Output: "x=0"

 Example 4:
 Input: "2x+3x-6x=x+2"
 Output: "x=-1"

 Example 5:
 Input: "x=x+2"
 Output: "No solution"

 */
public class _640 {
    public static class Solution1 {
        /**
         * Reference: https://discuss.leetcode.com/topic/95203/concise-java-solution/7
         */
        public String solveEquation(String equation) {
            String[] parts = equation.split("=");
            int[] left = evaluate(parts[0]);
            int[] right = evaluate(parts[1]);
            if (left[0] == right[0] && left[1] == right[1]) {
                return "Infinite solutions";
            } else if (left[0] == right[0]) {
                return "No solution";
            }
            return "x=" + (right[1] - left[1]) / (left[0] - right[0]);
        }

        private int[] evaluate(String part) {
            int[] result = new int[2];//result[0] is the coefficient for x, result[1] is the coefficient for constants
            String[] tokens = part.split("(?=[+-])");  // ()for match group; ?= for match and include in res; [+-] means + or -;
            for (String token : tokens) {
                if (token.equals("+x") || token.equals("x")) {
                    result[0]++;
                } else if (token.equals("-x")) {
                    result[0]--;
                } else if (token.contains("x")) {
                    result[0] += Integer.parseInt(token.substring(0, token.length() - 1));
                } else {
                    result[1] += Integer.parseInt(token);
                }
            }
            return result;
        }
    }
}
