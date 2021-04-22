package infLearn;

/**
 *
 *  Question 3 : [Max Depth Of Binary Tree]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */



public class Graph_MaxDepthOfBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    public static int solve(TreeNode root) {
        if(root == null) return 0;

        return Math.max(solve(root.left),solve(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("solve(root) = " + solve(root));
    }
}
