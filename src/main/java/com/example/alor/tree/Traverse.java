package com.example.alor.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class Traverse {
    public static void main(String[] args) {
        TreeNode root = TreeNode.demoTree();
        Printer.serialiseRecursive(root);
        Printer.printTheTree(root);
    }


    /**
     * left-right-root
     */
    public static void postOrder(TreeNode node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value+" ");
    }

    /**
     * left-root-right
     */
    public static void inOrder(TreeNode node) {
        if(node == null) return;
        inOrder(node.left);
        System.out.print(node.value+" ");
        inOrder(node.right);
    }

    /**
     * root-left-right
     */
    public static void preOrder(TreeNode node) {
        if(node==null) return;
        System.out.print(node.value+" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * root-left-right (level by level, no recursion)
     */
    public static void levelOrder(TreeNode node) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        while(node != null) {
            System.out.print(node.value + " ");
            if(node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
            node = q.poll();
        }
    }
}
