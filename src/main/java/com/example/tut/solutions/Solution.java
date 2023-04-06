package com.example.tut.solutions;

import com.google.common.collect.Lists;

import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author neo.zr
 * @since 2023/2/8
 */


public class Solution {

    /**
     * 合并两个有序的单向链表 [默认降序], 去掉重复元素
     */
    private Node mergeSortedLinkedList(Node first, Node second){
        Node dummy = new Node(Integer.MIN_VALUE);
        Node temp = dummy;
        Node next = null;
        Node tempFirst = first;
        Node tempSecond = second;
        while(tempFirst != null || tempSecond != null){
            if(tempFirst == null){
                next = tempSecond;
                tempSecond = tempSecond.next;
            }else if(tempSecond == null){
                next = tempFirst;
                tempFirst = tempFirst.next;
            }else{
                if(tempFirst.val > tempSecond.val){
                    next= tempFirst;
                    tempFirst = tempFirst.next;
                }else{
                    next = tempSecond;
                    tempSecond = tempSecond.next;
                }
            }
            if(!temp.val.equals(next.val)){
                temp.next = next;
                temp = next;
            }
        }

        return dummy.next;
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> m = new HashMap();
        Map<Character, Integer> uniques = new HashMap<>();
        for(int i = 0; i < (s.length()-minSize); ++i){
            String subS = s.substring(i, i+minSize);
            if(i == 0){
                for(int j = 0; j < subS.length(); ++j){
                    uniques.compute(subS.charAt(j), (k, v) -> v==null?1:v+1);
                }
            }else{
                uniques.compute(subS.charAt(subS.length()-1), (k, v) -> v==null?1:v+1);
                Character removeC = s.charAt(i-1);
                uniques.compute(removeC, (k, v) -> v - 1);
                if(uniques.get(removeC) == 0){
                    uniques.remove(removeC);
                }
            }
            if(uniques.size() > maxLetters){
                continue;
            }
            m.compute(subS, (k, v)->v==null?1:v+1);
        }
        return m.values().stream().max(Comparator.comparingInt(e->e)).orElse(0);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Set<Integer> sets = new HashSet<>();
        sets.add(3);
        sets.add(1);
        sets.add(2);

        List<Integer> l =
                sets.stream().sorted(Comparator.comparingInt(e->e)).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.println(l);
        System.out.println(Arrays.toString("abc".split("")));

        System.out.println(Boolean.TRUE.toString());

        System.out.println(Lists.newArrayList(1, 1, 2, 2, 3, 3, 3).stream().sorted(Comparator.comparingInt(e-> (int) e).reversed())
                .limit(2).collect(Collectors.toList()));

    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int top = 0;
        for(int i = 0; i < nums.length; ++i){
            count.compute(nums[i], (kk, v) -> v==null? 1: v+1);
        }
        List<Integer> topKCount = count.values().stream().sorted(Comparator.comparingInt(e-> (int) e).reversed())
                .limit(k).collect(Collectors.toList());
        int[] ret = new int[k];
        for(Map.Entry<Integer, Integer> entry: count.entrySet()){
            if(topKCount.contains(entry.getValue())){
                ret[--k] = entry.getKey();
                if(k == 0){
                    break;
                }
            }
        }
        return ret;
    }

    public int findLongestChain(int[][] pairs) {
        for(int i = 0; i < pairs.length; ++i){
            for(int j = i+1; j < pairs.length; ++j){
                if(pairs[i][0] > pairs[j][0]){
                    int[] temp = pairs[i];
                    pairs[i] = pairs[j];
                    pairs[j] = temp;
                }
            }
        }

        int size = 1;
        int idx = 0;
        for(int i = 1; i < pairs.length; ++i){
            if(pairs[i][0] > pairs[idx][1]){
                size++;
                idx = i;
            }else{
                if(pairs[i][1] < pairs[idx][1]){
                    idx = i;
                }
            }
        }

        return size;
    }

//
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         Stack<TreeNode> stack = new Stack<>();
//         TreeNode node = root;
//         while(node != null || !stack.isEmpty()){
//             while(node != null){
//                 stack.push(node);
//                 node = node.left;
//             }
//
//             if(!stack.isEmpty()){
//                 TreeNode top = stack.pop();
//
//                 node = top.right;
//             }
//         }
//    }


}
