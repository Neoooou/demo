package com.example.tutorial.basics.collections;

import java.util.*;

public class SortedMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>((e1, e2) -> e2 - e1);
        map.put(1, "a");
        map.put(3, "b");
        map.put(2, "c");
        map.put(4, "d");


        for (Integer key : map.keySet()) {
            System.out.println(key);
        }

        Map.Entry<Integer, String> entry = map.ceilingEntry(3);
        System.out.println(entry);
    }

    public int maxProduct(String[] words) {
        Map<Character, HashSet<String>> char2Word = new HashMap<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); ++i) {
                char2Word.putIfAbsent(word.charAt(i), new HashSet<>());
                char2Word.get(word.charAt(i)).add(word);
            }
        }

        TreeMap<Integer, List<String>> len2Word = new TreeMap<>((e1, e2) -> e2 - e1);
        for (String word : words) {
            int len = word.length();
            len2Word.putIfAbsent(len, new ArrayList<>());
            len2Word.get(len).add(word);
        }

        Integer lastLevelLen = null;
        for (Integer len : len2Word.keySet()) {
            if (lastLevelLen == null) {
                List<String> curLenWords = len2Word.get(len);
                for (int i = 0; i < curLenWords.size(); ++i) {
                    for (int j = i + 1; j < curLenWords.size(); ++j) {
                        if (!containCommonChar(curLenWords.get(i), curLenWords.get(j), char2Word)) {
                            return len * len;
                        }
                    }
                }
                lastLevelLen = len;
            } else {
                List<String> lastLenWords = len2Word.get(lastLevelLen);
                List<String> curLenWords = len2Word.get(len);
                for (int i = 0; i < lastLenWords.size(); ++i) {
                    for (int j = 0; j < curLenWords.size(); ++j) {
                        if (!containCommonChar(lastLenWords.get(i), curLenWords.get(j), char2Word)) {
                            return lastLevelLen * len;
                        }
                    }
                }

                for (int i = 0; i < curLenWords.size(); ++i) {
                    for (int j = i + 1; j < curLenWords.size(); ++j) {
                        if (!containCommonChar(curLenWords.get(i), curLenWords.get(j), char2Word)) {
                            return len * len;
                        }
                    }
                }
                lastLevelLen = len;
            }
        }

        return 0;
    }

    /**
     * time complexity O(C)
     *
     * @param word1
     * @param word2
     * @param char2Word
     * @return
     */
    private boolean containCommonChar(String word1, String word2, Map<Character, HashSet<String>> char2Word) {
        for (HashSet<String> set : char2Word.values()) {
            if (set.contains(word1) && set.contains(word2)) {
                return true;
            }
        }
        return false;
    }
}
