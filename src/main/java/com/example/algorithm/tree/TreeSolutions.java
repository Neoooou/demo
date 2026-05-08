package com.example.algorithm.tree;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class TreeSolutions {

    /**
     * 是否是镜像树
     */
    public void isSym(TreeNode root) {
        System.out.println("is sysmmetrical: " + isSymSubTree(root.left, root.right));
    }

    /**
     * 是否是镜像树
     */
    private boolean isSymSubTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.left == null && left.right == null
                && right.right == null && right.left == null) return left.value == right.value;
        boolean f = left.value == right.value;
        return f && isSymSubTree(left.left, right.right) && isSymSubTree(left.right, right.left);
    }

    /**
     * 最近相同祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.value > p.value && root.value > q.value) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.value < p.value && root.value < q.value) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    private Boolean isSubNode(TreeNode root, TreeNode sub) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                if (node.value == sub.value) {
                    return true;
                }
                stack.push(node);
                node = node.left;
            }

            if (!stack.isEmpty()) {
                TreeNode top = stack.pop();
                node = top.right;
            }
        }
        return false;
    }


    private static int getMedian(TreeNode n, int kth) {
        int leftLeave = n.left == null ? 0 : n.left.key;
        int dif = kth - leftLeave;
        if (dif == 1) {
            return n.value;
        } else if (dif > 1) {
            return getMedian(n.right, dif);
        } else {
            return getMedian(n.left, kth);
        }
    }

    public static TreeNode kthSmallest(TreeNode root, int k) {
        /**
         * 前序遍历，left - root - right, 得到升序的数组序列，取k个即可
         */
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            if(!stack.isEmpty()){
                TreeNode top = stack.pop();
                k --;
                if(k == 0){
                    return top;
                }
                node = top.right;
            }
        }

        return node;
    }


    public static TreeNode buildRandomTree(int n){
        BinarySearchTree tree = new BinarySearchTree();
        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < n; i++){
            tree.put(random.nextInt(100));
        }
        return tree.root;
    }

    public static void main(String[] args) {
        int n = 100, k = 11;
        TreeNode root = buildRandomTree(n);
        Traverse.inOrder(root);
        System.out.println("\n");

        List<TreeNode> inOrderList = collectInorder(root);
        inOrderList.forEach(e -> System.out.print(e.value + " "));

        TreeNode treeNode = inOrderList.get(k-1);
        System.out.println("\n" + treeNode.value);
        TreeNode answer = kthSmallest(root, k);
        System.out.println("\n" + answer.value);

    }

    public static List<TreeNode> collectInorder(TreeNode node){
        List<TreeNode> list = Lists.newArrayList();
        inOrder(node, list);
        return list;
    }
    public static void inOrder(TreeNode node, List<TreeNode> list) {
        if(node == null) return;
        inOrder(node.left, list);
        list.add(node);
        inOrder(node.right, list);
    }
}
