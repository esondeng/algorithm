package com.test.algorithm;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 * *
 * * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * *
 * * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * *
 * * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * *
 * * Examples:
 * * s = "3[a]2[bc]", return "aaabcbc".
 * * s = "3[a2[c]]", return "accaccacc".
 * * s = "2[abc]3[cd]ef", return "abcabccdcdcdef"
 * <p>
 * 思路，利用栈，数字栈和结果栈
 * 联想表达式计算：也是操作数栈 + 操作符栈，最后的结果在操作数栈里面
 *
 * @author dengxiaolin
 * @since 2020/12/10
 */
public class DecodeString {
    public static void main(String[] args) {
        System.out.println(decodeStr("2[abc]3[cd]ef"));
    }

    public static String decodeStr(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<String> result = new Stack<>();
        result.push("");

        char[] m = s.toCharArray();
        int index = 0;
        while (index < m.length) {
            int startIndex = index;
            if (isDigit(m[index])) {
                while (isDigit(m[index])) {
                    index++;
                }

                int size = Integer.parseInt(s.substring(startIndex, index));
                index--;
                count.push(size);
            }
            else if (m[index] == '[') {
                result.push("");
            }
            else if (m[index] == ']') {
                int size = count.pop();
                StringBuilder sb = new StringBuilder();
                String str = result.pop();
                for (int i = 0; i < size; i++) {
                    sb.append(str);
                }
                result.push(result.pop() + sb.toString());
            }
            // 字母
            else {
                result.push(result.pop() + m[index]);
            }

            index++;
        }

        return result.pop();
    }

    private static boolean isDigit(char char1) {
        return '0' <= char1 && char1 <= '9';
    }

    private static boolean isChar(char char1) {
        return ('a' <= char1 && char1 <= 'z')
                || ('A' <= char1 && char1 <= 'Z');
    }
}
