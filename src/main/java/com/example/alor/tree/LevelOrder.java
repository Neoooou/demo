package com.example.alor.tree;

import java.util.*;

/**
 * @author neo.zr
 * @since 2024/4/23
 */

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root != null)
            queue.add(root);
        boolean flag = false;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                level.add(node.value);
            }
            if(flag){
                Collections.reverse(level);
            }
            ret.add(level);
            flag = !flag;
        }
        return ret;

    }
}
