package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * 思路1：
 * 组合问题，深度优先回溯计算组合
 * 思路2：记住原来的结果
 *
 * @author dengxiaolin
 * @since 2020/11/12
 */
public class LetterCombination {

    public static void main(String[] args) {
        LetterCombination letterCombination = new LetterCombination();
        List<String> result = letterCombination.letterCombinations2("23");
        result.forEach(System.out::println);

    }


    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[][] charArray = new char[][] {
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };


        int[] digitArray = new int[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            digitArray[i] = digits.charAt(i) - '0';
        }

        letterCombinations(digitArray, charArray, 0, new Stack<>(), result);

        return result;
    }


    private void letterCombinations(int[] digits,
                                    char[][] charArray,
                                    int startRow,
                                    Stack<Character> path,
                                    List<String> result) {
        if (path.size() == digits.length) {
            StringBuilder sb = new StringBuilder();
            for (Character character : path) {
                sb.append(character);
            }
            result.add(sb.toString());
        }
        else {
            int digit = digits[startRow];
            char[] chars = charArray[digit];

            for (int column = 0; column < chars.length; column++) {
                path.push(chars[column]);
                letterCombinations(digits, charArray, startRow + 1, path, result);
                path.pop();
            }
        }
    }

    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        char[][] charArray = new char[][] {
                {},
                {},
                {'a', 'b', 'c'},
                {'d', 'e', 'f'},
                {'g', 'h', 'i'},
                {'j', 'k', 'l'},
                {'m', 'n', 'o'},
                {'p', 'q', 'r', 's'},
                {'t', 'u', 'v'},
                {'w', 'x', 'y', 'z'}
        };

        result.add("");
        // 依赖于原来的结果
        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            List<String> tempResult = new ArrayList<>();
            for (char c : charArray[digit]) {
                result.forEach(t -> tempResult.add(t + c));
            }

            result = tempResult;
        }

        return result;

    }

}
