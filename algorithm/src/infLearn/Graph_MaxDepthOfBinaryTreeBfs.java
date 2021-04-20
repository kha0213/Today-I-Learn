package infLearn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * Question 5 : [Max Depth Of Binary Tree (BFS)]
 *
 * Created by Kim Young Long.
 * Date : 2021-04-20.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */

public class Graph_MaxDepthOfBinaryTreeBfs {
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            this.val = x;
        }
    }

    public static int solve(TreeNode root) {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left!=null) {
                    queue.offer(node.left);
                }
                if(node.right!=null) {
                    queue.offer(node.right);
                }
            }
            count++;
        }
        return count;
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

