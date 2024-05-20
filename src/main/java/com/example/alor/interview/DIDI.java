package com.example.alor.interview;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个链表，删除链表的倒数第N个结点，并且返回链表的头结点。实现时间复杂度O(L),L是链表的长度
 * 示例：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * @author neo.zr
 * @since 2024/1/30
 */

public class DIDI {

    public static void main(String[] args) {
        DIDI o = new DIDI();
        // head:null
        LinkNode head = mockHead();

        int n = 0;
        System.out.println("Case 1, n < 1：");
        printLinkNode(o.deleteLastNNode(head, n));


        n = 100;
        System.out.println("Case 1, n out of range of linkNode");
        printLinkNode(o.deleteLastNNode(head, n));

        head = mockHead();
        n = 1;
        System.out.println("Case 1, n = 1");
        printLinkNode(o.deleteLastNNode(head, n));

        head = mockHead();
        n = 2;
        System.out.println("Case 2, n = 2");
        printLinkNode(o.deleteLastNNode(head, n));

        head = mockHead();
        n = 3;
        System.out.println("Case 3, n = 3");
        printLinkNode(o.deleteLastNNode(head, n));
    }

    private static LinkNode mockHead() {
        LinkNode head = new LinkNode();
        head.val = 1;

        LinkNode node2 = new LinkNode();
        node2.val = 2;

        LinkNode node3 = new LinkNode();
        node3.val = 3;

        head.next = node2;
        node2.next = node3;
        return head;
    }

    private static void printLinkNode(LinkNode node){
        LinkNode tmp = node;
        while(tmp != null){
            System.out.print(tmp.val + " ->");
            tmp = tmp.next;
        }

        System.out.print("\n");
    }
    /**
     * head： 头节点
     * n: 倒数第几个节点， 1 - 倒数第一个， 负数或者大于LinkList长度无效
     */
    public LinkNode deleteLastNNode(LinkNode head, int n){
//        /**
//         * 解法 1：两次遍历，时间：O（N）， 空间：O（1）
//         */
//        int len = len(head);
//        int k = len - n;
//        // 遍历的长度： N+k


        /**
         * 解法 2：存储所有节点，一次遍历，时间：O（N）， 空间：O（N）
         */
        if(n < 1){
            return head;
        }

        // 下标对应的上一个节点，0 - dummy， 1 - head -1， 2 - head.next -2, 3 - 3
        Map<Integer, LinkNode> idx2Pre = new HashMap<Integer, LinkNode>();
        LinkNode dummy = new LinkNode();
        dummy.next = head;

        idx2Pre.put(0, dummy);

        LinkNode tmp = head;
        int idx = 1;
        while(tmp != null){
            idx2Pre.put(idx++, tmp);
            tmp = tmp.next;
        }

        System.out.println(idx2Pre);

        int lenOfLinkNode = idx2Pre.size() - 1;
        if(n > lenOfLinkNode){
            return head;
        }

        int m = lenOfLinkNode - n;
        LinkNode mPre = idx2Pre.get(m);
        if(mPre.next != null){
            mPre.next = mPre.next.next;
        }

        return dummy.next;
    }

    /**
     *
     */
    private int len(LinkNode head){
        LinkNode tmp = head;
        int len = 0;
        while(tmp != null){
            len++;
        }
        return len;
    }

    @Data
    static class LinkNode{

        int val;

        LinkNode next;
    }

}
