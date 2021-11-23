package leetcode.problems.binarysearch;

public class LC_BinaryTreeTilt {

    public int findTilt(TreeNode root) {
        if (root == null)
            return 0;
        int sum = 0;
        sum += Math.abs(sumNode(root.left) - sumNode(root.right));
        sum += findTilt(root.left);
        sum += findTilt(root.right);
        return sum;
    }

    public int sumNode(TreeNode node) {
        if (node == null)
            return 0;
        return node.val + sumNode(node.left) + sumNode(node.right);
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
