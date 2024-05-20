package com.example.tutorial;



import java.util.*;

/**
 * @author neo.zr
 * @since 2024/1/15
 */

public class SerializationDemo {
    public static void main(String[] args) {
        List<Integer> e = new ArrayList<>();
        e.add(1);
        e.add(3);
        e.add(2);
        e.sort(Comparator.comparingInt(a -> a));
        System.out.println(e);

        Integer i = 100;
        System.out.println(Integer.toHexString(0));
        System.out.println(Integer.toHexString(255));
        System.out.println(Integer.toHexString(25));
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Map<String, Integer> root2Len = new HashMap<String, Integer>();

        Map<Character, List<Integer>> firstChar2Len = new HashMap<Character, List<Integer>>();

        Set<String> uniDic = new HashSet<>(dictionary);
        for (String root : uniDic) {
            char firstChar = root.charAt(0);
            firstChar2Len.putIfAbsent(firstChar, new LinkedList<>());
            firstChar2Len.get(firstChar).add(root.length());
            root2Len.put(root, root.length());
        }

        for (Map.Entry<Character, List<Integer>> entry : firstChar2Len.entrySet()) {
            entry.getValue().sort(Comparator.comparingInt(e -> e));
        }

        char c;
        String[] words = sentence.split(" ");
        int j = 0;
        while(j < words.length){
            c = words[j].charAt(0);
            List<Integer> lens = firstChar2Len.get(c);
            if (lens != null) {
                for (int i=0; i < lens.size(); ++i) {
                    Integer len = lens.get(i);
                    if(len > words[j].length()){
                        break;
                    }
                    String subword = words[j].substring(0, len);

                    if (len.equals(root2Len.get(subword))) {
                        words[j] = subword;
                        break;
                    }
                }
            }
        }

        return String.join(" ", words);
    }

}
