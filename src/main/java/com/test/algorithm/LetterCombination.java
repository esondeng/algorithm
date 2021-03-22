package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先回溯计算组合
 *
 * @author dengxiaolin
 * @since 2020/11/12
 */
public class LetterCombination {

    public static void main(String[] args) {
        LetterCombination letterCombination = new LetterCombination();
        List<String> result = letterCombination.letterCombinations("23");
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
            digitArray[i] = Integer.valueOf(String.valueOf(digits.charAt(i)));
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
            return;
        }
        else {
            // 递归只管当前循环，体会多重循环的区别
            int digit = digits[startRow];
            for (int column = 0; column < charArray[digit].length; column++) {
                path.push(charArray[digit][column]);
                letterCombinations(digits, charArray, startRow + 1, path, result);
                path.pop();
            }
        }
    }
}
