package com.example.algorithm.leetcode;

import com.example.algorithm.tree.TreeNode;

import java.util.Stack;

public class KthSmallest {

    // left - root - right 中序遍历
    public int solve(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }

            if(!stack.isEmpty()){
                TreeNode top = stack.pop();
                k--;
                if(k == 0){
                    return top.value;
                }
                node = top.right;
            }
        }

        return -1;
    }
}
