package com.example.algorithm.tree;

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
        System.out.println("is sysmmetrical: " + isSymSubTree(root.left,root.right));
    }

    /**
     * 是否是镜像树
     */
    private boolean isSymSubTree(TreeNode left, TreeNode right) {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.left == null && left.right == null
                && right.right == null && right.left == null) return left.value == right.value;
        boolean f = left.value == right.value;
        return f && isSymSubTree(left.left, right.right) && isSymSubTree(left.right, right.left);
    }

    /**
     * 最近相同祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.value > p.value && root.value > q.value){
            return lowestCommonAncestor(root.left, p, q);
        }else if (root.value < p.value && root.value < q.value){
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }

    private Boolean isSubNode(TreeNode root, TreeNode sub){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                if(node.value == sub.value){
                    return true;
                }
                stack.push(node);
                node = node.left;
            }

            if(!stack.isEmpty()){
                TreeNode top = stack.pop();
                node = top.right;
            }
        }
        return false;
    }



    private static int getMedian(TreeNode n,int kth) {
        int leftLeave = n.left == null ?0 : n.left.key;
        int dif = kth - leftLeave;
        if(dif == 1) {
            return n.value;
        }else if(dif > 1) {
            return getMedian(n.right,dif);
        }else {
            return getMedian(n.left,kth);
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return -1;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode node = root;
        while(!stack.isEmpty()) {
            //Left first
            while (node != null && node.left != null) {
                stack.add(node.left);
                node = node.left;
            }
            //Process left/curr
            node = stack.pop();
            k--;
            if (k == 0) {
                return node.value;
            }
            node = node.right;
            if (node != null) {
                stack.push(node);
            }
        }

        return -1;
    }

}
