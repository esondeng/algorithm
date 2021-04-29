package com.test.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 * <p>
 * 思路，linkedHashMap存取字符串，长度大的放前面
 * <p>
 * Example 1:
 * <p>
 * Input: s = "III"
 * Output: 3
 * Example 2:
 * <p>
 * Input: s = "IV"
 * Output: 4
 * Example 3:
 * <p>
 * Input: s = "IX"
 * Output: 9
 * Example 4:
 * <p>
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 * <p>
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * @author dengxiaolin
 * @since 2021/04/28
 */
public class RomanToInt {
    public static int romanToInt(String s) {
        Map<String, Integer> map = new LinkedHashMap<>(16);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("IV", 4);
        map.put("IX", 9);

        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        int i = 0;
        int result = 0;
        while (i < s.length()) {
            String s1 = s.substring(i);
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (s1.startsWith(entry.getKey())) {
                    result += entry.getValue();
                    i += entry.getKey().length();
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
