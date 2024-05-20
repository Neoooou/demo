package com.example.alor.leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author neo.zr
 * @since 2023/4/6
 */

public class WordLadder {
    /**
     * @param args
     */
    public static void main(String[] args) {
        WordLadder ladder = new WordLadder();
        List<String> dictionary = ladder.readWords();
        Word wordLadder = ladder.findLadder("limp", "cake", dictionary);
        ladder.printLadder(wordLadder);
    }

    private void printLadder(Word wordLadder) {
        if(wordLadder == null) {
            System.out.println();
            return;
        }
        printLadder(wordLadder.parent);
        System.out.print(wordLadder.word+" -> ");

    }

    private Word findLadder(String source, String dest, List<String> dictionary) {
        if (null == source || null == dest || source.trim().length() == 0 || dest.trim().length() == 0
                || null == dictionary || dictionary.size() == 0) return null;
        if (source.length() != dest.length()) return null;
        Queue<Word> queue = new LinkedList<Word>();
        Word start = new Word(source);
        queue.add(start);
        while (!queue.isEmpty()) {
            Word current = queue.poll();
            char[] sChars = current.word.toCharArray();
            for (int j=0; j<sChars.length; j++){
                char original=sChars[j];
                for (char c='a'; c<='z'; c++){
                    if (c==sChars[j]) continue;
                    sChars[j]=c;
                    String tempStr=String.copyValueOf(sChars);
                    Word newWord = new Word(tempStr);
                    newWord.parent = current;
                    if (tempStr.equals(dest)){
                        return newWord;
                    }
                    if (dictionary.contains(tempStr)){
                        queue.add(newWord);
                        dictionary.remove(tempStr);
                    }
                }
                sChars[j]=original;
            }
        }
        return null;
    }

    private List<String> readWords() {
        List<String> words = new ArrayList<String>();
        try {
            String root = System.getProperty("user.dir");
            System.out.println(root);
            BufferedReader br = new BufferedReader(new FileReader("src/wordfile.txt"));
            String word = null;
            while ((word = br.readLine()) != null) {
                words.add(word.toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    class Word {
        String word;
        Word   parent;// the parent, is only is to find the path recursively

        public Word(String word) {
            this.word = word;
        }

    }
}
