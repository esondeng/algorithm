package com.test.word;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author dengxiaolin
 * @since 2020/11/13
 */
public class ReplaceWord {
    public String replaceWords(List<String> dictionary, String sentence) {
        dictionary.sort(Comparator.comparingInt(String::length));

        StringBuilder sb = new StringBuilder();
        Arrays.stream(sentence.split(" ")).forEach(t -> {
            boolean isPrefix = false;
            String prefix = null;
            for (String word : dictionary) {
                if (t.startsWith(word)) {
                    isPrefix = true;
                    prefix = word;
                    break;
                }
            }

            if (isPrefix) {
                sb.append(prefix).append(" ");
            }
            else {
                sb.append(t).append(" ");
            }
        });
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
