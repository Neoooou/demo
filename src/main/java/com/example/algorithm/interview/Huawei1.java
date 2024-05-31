package com.example.algorithm.interview;


import java.util.*;

/**
 * @author neo.zr
 * @since 2023/8/27
 */

public class Huawei1 {
    // {parent: [child, child]...}
    final static Map<String, Set<String>> data = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int col = Integer.parseInt(in.nextLine());
        while (col-- > 0) {
            String line = in.nextLine();
            buildByLine(line);
        }

        String arg = in.nextLine();
        List<String> res = new ArrayList<>();
        if (data.containsKey(arg)) {
            res.addAll(data.get(arg));
        }
        int i = 0;
        while (i < res.size()) {
            if (data.containsKey(res.get(i))) {
                res.addAll(data.get(res.get(i)));
            }
            i++;
        }
        res.sort(Comparator.naturalOrder());
        res.forEach(e -> System.out.println(e));
    }

    private static void buildByLine(String line) {
        String[] nodes = line.split(" ");
        String child = nodes[0], parent = nodes[1];
        if (data.containsKey(parent)) {
            data.get(parent).add(child);
        } else {
            Set<String> s = new HashSet<>();
            s.add(child);
            data.put(parent, s);
        }
    }
}
