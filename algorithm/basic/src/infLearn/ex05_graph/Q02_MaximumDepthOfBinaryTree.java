package infLearn.ex05_graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q02_MaximumDepthOfBinaryTree {
    public static void main(String[] args){
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(4);
        tree.left.left = new TreeNode(5);
        tree.left.right = new TreeNode(8);
        tree.left.left.left = new TreeNode(7);
        //tree.left.left.left.left = new TreeNode(11);

        /* 이런 형태의 노트
               3
             1    4
           5   8
         7
         */

        System.out.println("dfs : " + solution_dfs(tree));
        System.out.println("bfs : " + solution_bfs(tree));
        System.out.println("recursive : " + solution_recursive(tree));
    }

    public static int solution_recursive(TreeNode root) {
        if ( root == null )
            return 0;
        int leftMax = solution_recursive(root.left);
        int rightMax = solution_recursive(root.right);
        return Math.max(leftMax, rightMax) + 1;
    }


    public static int solution_dfs(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();
        stack.push(root);
        depthStack.push(1);
        int result = 0;

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int depth = depthStack.pop();
            result = Math.max(result, depth);

            if (node.left != null) {
                stack.push(node.left);
                depthStack.push(depth + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depthStack.push(depth + 1);
            }
        }
        return result;
    }

    private static int solution_bfs(TreeNode tree) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        int result = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }

            result++;
        }

        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            this.val = x;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
