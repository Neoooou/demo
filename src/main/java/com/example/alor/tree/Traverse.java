package com.example.alor.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class Traverse {
    public static void main(String[] args) {
        TreeNode root = TreeNode.demoTree();
        System.out.print("Post order traverse\n");
        postOrder(root);
        System.out.print("\n");
        postOrderNoRec(root);

        System.out.print("\nIn order traverse\n");
        inOrder(root);
        System.out.print("\n");
        inOrderNoRec(root);

        System.out.print("\nPre order traverse\n");
        preOrder(root);
        System.out.print("\n");
        preOrderNoRec(root);

        System.out.print("\nLevel order traverse\n");
        levelOrder(root);
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

    public static void postOrderNoRec(TreeNode node){
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();

        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            if(!stack.isEmpty()){
                node = stack.pop();

                if(node.right == null || node.right == pre){
                    // handle here
                    System.out.print(node.value + " ");
                    pre = node;
                    node = null;
                }else{
                    stack.push(node);
                    node = node.right;
                }
            }
        }
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

    public static void inOrderNoRec(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            if(!stack.isEmpty()){
                TreeNode top = stack.pop();

                // handle here
                System.out.print (top.value + " ");

                node = top.right;
            }
        }
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

    public static void preOrderNoRec(TreeNode node){
        Stack<TreeNode> stack = new Stack<>();
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);

                // handle here
                System.out.print (node.value + " ");

                node = node.left;
            }

            if(!stack.isEmpty()){
                TreeNode top = stack.pop();
                node = top.right;
            }
        }
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
