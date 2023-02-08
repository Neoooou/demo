package com.example.interview;

import lombok.Getter;

/**
 *
 * @author neo.zr
 * @since 2023/2/8
 */


public class Ques1 {

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
            if(!temp.getVal().equals(next.getVal())){
                temp.next = next;
                temp = next;
            }
        }

        return dummy.next;
    }


    static class Node{
        @Getter
        Integer val;
        Node next;
        Node(Integer val){
            this.val = val;
        }
        Node(Integer val, Node next){
            this.val = val;
            this.next = next;
        }
    }

}
