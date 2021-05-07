package com.test.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 广度遍历获取最短路径, 这里可以使用双向BFS
 *
 * @author dengxiaolin
 * @since 2020/11/05
 */
public class LadderLength {

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        System.out.println(ladderLength("hit", "cog", wordList));

    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);

        // 从两端 Bfs 遍历要用的队列
        Queue<String> queue1 = new LinkedList<>();
        Set<String> visited1 = new HashSet<>();
        queue1.offer(beginWord);
        visited1.add(beginWord);

        Queue<String> queue2 = new LinkedList<>();
        Set<String> visited2 = new HashSet<>();
        queue2.offer(endWord);
        visited2.add(endWord);

        int count = 0;
        Set<String> allWordSet = new HashSet<>(wordList);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;

            // 从queue小的方向走
            if (queue1.size() > queue2.size()) {
                Queue<String> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;

                Set<String> t = visited1;
                visited1 = visited2;
                visited2 = t;
            }

            int size = queue1.size();
            for (int i = 0; i < size; i++) {
                String word = queue1.poll();
                for (String allword : allWordSet) {
                    if (diffOneChar(word, allword)) {
                        if (visited1.contains(allword)) {
                            continue;
                        }

                        if (visited2.contains(allword)) {
                            return count + 1;
                        }

                        queue1.offer(allword);
                        visited1.add(allword);
                    }
                }
            }
        }

        return 0;
    }

    private static boolean diffOneChar(String beginWord, String endWord) {
        if (beginWord.length() != endWord.length()) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) {
                count++;
            }
        }

        return count == 1;
    }
}
