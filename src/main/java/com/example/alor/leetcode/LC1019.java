package com.example.alor.leetcode;

/**
 * @author neo.zr
 * @since 2024/2/21
 */

public class LC1019 {
    public int[] nextLargerNodes(ListNode head) {
        int len = len(head);
        ListNode[] nodes = toArr(head);

        int[] ans = new int[len];
        int max = Integer.MIN_VALUE;
        for (int i = len - 1; i > -1; i--) {
            if(i+1 < len && nodes[i].val < max){
                for(int j = i +1; j < len; ++j){
                    if(nodes[j].val > nodes[i].val){
                        ans[i] = nodes[j].val;
                        break;
                    }
                }
            }else{
                ans[i] = 0;
            }
            max = Math.max(max, nodes[i].val);
        }
        return ans;
    }

    private ListNode[] toArr(ListNode node) {
        int len = len(node);
        ListNode[] nodes = new ListNode[len];
        ListNode tmp = node;
        int i = 0;
        while (tmp != null) {
            nodes[i] = tmp;
            tmp = tmp.next;
            i++;
        }
        return nodes;
    }

    private int len(ListNode node) {
        ListNode tmp = node;
        int i = 0;
        while (tmp != null) {
            tmp = tmp.next;
            i++;
        }
        return i;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
