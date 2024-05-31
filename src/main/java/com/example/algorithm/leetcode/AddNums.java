package com.example.algorithm.leetcode;

/**
 * @author neo.zr
 * @since 2024/1/25
 */

public class AddNums {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode temp1 = l1, temp2 = l2;
        ListNode head = null, prev = null;
        int val;
        while (temp1 != null && temp2 != null) {
            val = carry;
            if (temp1 != null) {
                val += temp1.val;
            }
            if (temp2 != null) {
                val += temp2.val;
            }

            carry = val / 10;
            ListNode cur = new ListNode(val % 10);

            if (head == null) {
                head = cur;
            }
            if (prev != null) {
                prev.next = cur;
            }
            prev = cur;
        }
        if (carry > 0) {
            prev.next = new ListNode(carry);
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


