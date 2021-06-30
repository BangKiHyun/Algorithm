package leetcode.problems.bfs;

import java.util.*;

public class LC_BinaryTreeZigzag {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> answerList = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int number = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            if (number % 2 == 0) {
                List<Integer> reverseNumbers = new ArrayList<>();
                for (int count = 0; count < size; count++) {
                    final TreeNode curNode = q.poll();
                    if (curNode != null) {
                        reverseNumbers.add(curNode.val);
                        if (curNode.left != null) q.add(curNode.left);
                        if (curNode.right != null) q.add(curNode.right);
                    }
                }
                if (!reverseNumbers.isEmpty()) {
                    Collections.reverse(reverseNumbers);
                    answerList.add(reverseNumbers);
                }
            } else {
                List<Integer> orderNumbers = new ArrayList<>();
                for (int count = 0; count < size; count++) {
                    final TreeNode curNode = q.poll();
                    if (curNode != null) {
                        orderNumbers.add(curNode.val);
                        if (curNode.left != null) q.add(curNode.left);
                        if (curNode.right != null) q.add(curNode.right);
                    }
                }
                if (!orderNumbers.isEmpty()) {
                    answerList.add(orderNumbers);
                }
            }
            number++;
        }
        return answerList;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
