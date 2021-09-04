package leetcode.problems.bst;

public class LC_DeleteNode {
    public static void main(String[] args) {
        final LC_DeleteNode solution = new LC_DeleteNode();
        TreeNode root = new TreeNode(5, new TreeNode(3), new TreeNode(6));
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        System.out.println(solution.deleteNode(root, 3));
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode node = findExistNode(root.right);
            root.val = node.val;
            node.val = key;

            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode findExistNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
