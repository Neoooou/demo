package com.example.algorithm.tree;

import java.util.Stack;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class BinarySearchTree {
    public TreeNode root;

    private int getLeaveCount(TreeNode n) {
        if(n == null) return 0;
        return (n.left == null ? 0:n.left.key+1) + (n.right == null ? 0: n.right.key+1);
    }

    private static final boolean RED   = true;

    private static final boolean BLACK = false;


    private boolean isRed(TreeNode x) {
        if(x == null) return false;
        return x.color == RED;
    }

    public void put(int value){
        root = put(root,1,value);
    }

    private TreeNode put(TreeNode h, int key, int value)
    {
        if (h == null) return new TreeNode(key, value, RED);
        int cmp = value - h.value;
        if      (cmp  < 0) h.left  = put(h.left,  1, value);
        else h.right = put(h.right, 1, value);
        h.key++;
        if (isRed(h.right) && !isRed(h.left))     h = rotateLeft(h);
        if (isRed(h.left)  && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  && isRed(h.right))     flipColors(h);
        return h;
    }

    private TreeNode resetRoot(TreeNode root) {
        int leftLeave = root.left == null? 0 : root.left.key;
        int rightLeave = root.right == null? 0 : root.right.key;
        TreeNode x ;
        if(leftLeave - rightLeave >= 1) {


            x = root.left;

            //change key


            root.left = x.right;
            x.right = root;

            return x;
        }else if(rightLeave - leftLeave >= 2) {
            x = root.right;

            //change key
            int a = x.right == null?0 : x.right.key;
            int b = root.left == null?0 : root.left.key;
            root.key = root.key - a - 1;
            x.key = x.key + b + 1;

            root.right = x.left;
            x.left = root;
            return x;
        }
        return root;

    }
    private TreeNode rotateLeft(TreeNode h)
    {
        assert isRed(h.right);
        TreeNode x = h.right;

        //change key
        int a = x.right == null?0 : x.right.key;
        int b = h.left == null?0 : h.left.key;
        h.key = h.key - a - 1;
        x.key = x.key + b + 1;

        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;


        return x;
    }
    private TreeNode rotateRight(TreeNode h)
    {
        assert isRed(h.left);
        TreeNode x = h.left;
        //change key
        int a = x.left == null?0 : x.left.key;
        int b = h.right == null?0 : h.right.key;
        h.key = h.key - a - 1;
        x.key = x.key + b + 1;

        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(TreeNode h)
    {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


    public int get(int  key)
    {
        TreeNode x = root;
        while (x != null)
        {
            int cmp = key - x.key;
            if      (cmp  < 0) x = x.left;
            else if (cmp  > 0) x = x.right;
            else if (cmp == 0) return x.value;
        }
        return -1;
    }
}
