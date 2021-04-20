package infLearn;

import java.util.Stack;

/**
 *
 * Question 4 : [Max Depth Of Binary Tree (DPS)]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */

public class Graph_MaxDepthOfBinaryTreeDps {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    public static int solve(TreeNode root) {
        if(root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int max = 0;
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            int count = value.pop();
            max = Math.max(max, count);

            if(pop.left != null) {
                stack.push(pop.left);
                value.push(1 + count);
            }
            if(pop.right != null) {
                stack.push(pop.right);
                value.push(1 + count);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(3);
        System.out.println("solve(root) = " + solve(root));
    }

}

