package leetcode.problems.bst;

public class LC_ValidateBST {
    private TreeNode preNode = null;

    public static void main(String[] args) {
        final LC_ValidateBST solution = new LC_ValidateBST();
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(4));
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        System.out.println(solution.isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }
        if (preNode != null && preNode.val >= root.val) {
            return false;
        }
        preNode = root;
        return isValidBST(root.right);
    }

    public static class TreeNode {
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