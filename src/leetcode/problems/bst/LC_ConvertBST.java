package leetcode.problems.bst;

public class LC_ConvertBST {
    private static int preData = 0;

    public static void main(String[] args) {
        Integer[] input = {4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8};
        TreeNode root = createTreeNode(input);
        final TreeNode treeNode = convertBST(root);
        treeNode.inOrder(treeNode);
    }

    public static TreeNode convertBST(TreeNode root) {
        if(root == null) return null;
        convertBST(root.right);
        preData += root.val;
        root.val = preData;
        convertBST(root.left);
        return root;
    }

    private static TreeNode createTreeNode(Integer[] input) {
        LinkedTree linkedTree = new LinkedTree();
        for (Integer data : input) {
            if(data == null) continue;
            linkedTree.insert(data);
        }
        return linkedTree.root;
    }

    private static class LinkedTree {
        private TreeNode root;

        public LinkedTree() {
        }

        public void insert(Integer data) {
            root = insertData(root, data);
        }

        private TreeNode insertData(TreeNode root, Integer data) {
            TreeNode treeNode = root;
            if (root == null) {
                return new TreeNode(data);
            }
            if (data < treeNode.val) {
                treeNode.left = insertData(treeNode.left, data);
            }
            if (data > treeNode.val) {
                treeNode.right = insertData(treeNode.right, data);
            }
            return treeNode;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
            left = null;
            right = null;
        }

        TreeNode(int val) {
            this.val = val;
        }

        public void inOrder(TreeNode root) {
            if (root != null) {
                root.inOrder(root.left);
                System.out.println(root.val);
                root.inOrder(root.right);
            }
        }
    }
}
