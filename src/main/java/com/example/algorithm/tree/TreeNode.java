package com.example.algorithm.tree;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class TreeNode {

    public Integer key;

    public Integer value;

    public TreeNode left,right;

    public boolean color;

    public TreeNode(int value) {
        super();
        this.value = value;
    }

    public TreeNode(int key,int value,boolean color){
        this.key = key;
        this.value = value;
        this.color = color;
    }

    public String toString() {
        return "color " + color + " key " + key + " value " + value;
    }

    public static TreeNode demoTree(){
        TreeNode root = new TreeNode(4);

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        root.left = n2;
        root.right = n6;

        n2.left = n1;
        n2.right = n3;

        n6.right = n7;
        n6.left = n5;

        return root;
    }
}
