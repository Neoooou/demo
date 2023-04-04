package com.example.alor.tree;

/**
 * @author neo.zr
 * @since 2023/4/4
 */

public class Printer {
    public final static String horz = "=";
    public final static String vert = "⥯";
    public final static String upLeft = "⥥";
    public final static String lowLeft = "⥣";
    public final static String space = "*";
    public final static String spaces = "**";

    public static void serialiseRecursive(TreeNode t) {
        if(t != null) {
            System.out.print(t.value+",");
            serialiseRecursive(t.left);
            serialiseRecursive(t.right);
        }else {
            System.out.print("$,");
        }
    }

    public static void printTheTree(TreeNode node) {
        printTheTree(node,"");
    }

    public static void printTheTree(TreeNode node,String data) {
        if(node == null) return;
        String rightData = spaces+upLeft+horz;
        String leftData = spaces+lowLeft+horz;
        String temp = "";
        if(data.indexOf(upLeft) != -1) {//right node
            String str = data.substring(0, data.indexOf(upLeft));
            rightData = str+spaces+upLeft+horz;
            leftData = str+vert+space+lowLeft+horz;
        }else if(data.indexOf(lowLeft) != -1){//left node
            String str = data.substring(0, data.indexOf(lowLeft));
            leftData = str+spaces+lowLeft+horz;
            rightData = str+vert+space+upLeft+horz;
        }
        printTheTree(node.right,rightData);
        if(data == "") {
            System.out.println("=="+node.value);
        }else {
            System.out.println(data+node.value);
        }
        printTheTree(node.left, leftData);
    }
}
