package com.example.tutorial.basics;


import lombok.Data;

import java.util.Stack;

/**
 * @author neo.zr
 * @since 2023/3/23
 */

public class DataStructureDemo {

    public int kthSmallest(TreeNode root, int k) {
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
                    return top.val;
                }
                node = top.right;
            }
        }

        return -1;

    }

    @Data
    class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
    }
}
