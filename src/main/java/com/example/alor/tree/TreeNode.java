package com.example.alor.tree;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class TreeNode {

    public int key;

    public int value;

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

        root.left = n1;
        root.right = n2;

        n1.left = n3;
        n1.right = n7;

        n2.right = n5;
        n2.left = n6;

        return root;
    }
}
