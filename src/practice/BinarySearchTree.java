package practice;

public class BinarySearchTree {

    public static void main(String[] args) {
        LinkedTree linkedTree = new LinkedTree(new TreeNode(5));
        linkedTree.insetNode(2);
        linkedTree.insetNode(1);
        linkedTree.insetNode(7);
        linkedTree.insetNode(10);
        linkedTree.insetNode(8);
        linkedTree.insetNode(3);

        System.out.println("Binary Tree");
        linkedTree.printBST();
    }

    private static class LinkedTree {
        private TreeNode root;

        public LinkedTree(TreeNode root) {
            this.root = root;
        }

        public void insetNode(int data) {
            root = insertKey(root, data);
        }

        public TreeNode search(int inputData) {
            TreeNode curNode = root;
            while (curNode != null) {
                if (inputData < curNode.data) {
                    curNode = curNode.left;
                } else if (inputData > curNode.data) {
                    curNode = curNode.right;
                } else break;
            }
            return curNode;
        }

        public void inOrder(TreeNode root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print(root.data + " ");
                inOrder(root.right);
            }
        }

        public void printBST() {
            inOrder(root);
        }

        private TreeNode insertKey(TreeNode root, int inputData) {
            TreeNode curNode = root;
            if (curNode == null) {
                return new TreeNode(inputData);
            }
            if (inputData < curNode.data) {
                curNode.left = insertKey(curNode.left, inputData);
            }
            if (inputData > curNode.data) {
                curNode.right = insertKey(curNode.right, inputData);
            }
            return curNode;
        }
    }

    private static class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
            this.left = null;
            this.right = null;
        }

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}
