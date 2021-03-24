package programmers.binarysearch.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class pg_길_찾기_게임 {
    private static int answerIdx = 0;

    public static void main(String[] args) {
        int[][] nodeinfo = {
                {5, 3},
                {11, 5},
                {13, 3},
                {3, 5},
                {6, 1},
                {1, 3},
                {8, 6},
                {7, 2},
                {2, 2}};
        for (int[] answer : solution(nodeinfo)) {
            for (int node : answer) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }

    public static int[][] solution(int[][] nodeinfo) {
        List<Pos> posList = new ArrayList<>();
        for (int idx = 0; idx < nodeinfo.length; idx++) {
            int raw = nodeinfo[idx][1];
            int col = nodeinfo[idx][0];
            posList.add(new Pos(idx + 1, raw, col));
        }
        Collections.sort(posList);
        Pos rootPos = posList.get(0);

        TreeNode rootNode = new TreeNode(rootPos.idx, rootPos.col);
        LinkedTree linkedTree = new LinkedTree(rootNode);
        for (int idx = 1; idx < posList.size(); idx++) {
            final Pos pos = posList.get(idx);
            linkedTree.insertNode(pos.idx, pos.col);
        }

        int[][] answer = new int[2][posList.size()];
        linkedTree.preOrder(rootNode, answer);
        answerIdx = 0;
        linkedTree.postOrder(rootNode, answer);
        return answer;
    }

    private static class LinkedTree {
        private TreeNode root;

        public LinkedTree(TreeNode root) {
            this.root = root;
        }

        public void insertNode(int idx, int data) {
            this.root = insertKey(root, idx, data);
        }

        private TreeNode insertKey(TreeNode root, int idx, int data) {
            TreeNode curNode = root;
            TreeNode newNode = new TreeNode(idx, data);
            if (curNode == null) {
                return newNode;
            }
            if (curNode.data > newNode.data) {
                curNode.left = insertKey(curNode.left, idx, data);
                return curNode;
            }
            if (curNode.data < newNode.data) {
                curNode.right = insertKey(curNode.right, idx, data);
                return curNode;
            }
            return curNode;
        }

        public void preOrder(TreeNode root, int[][] answer) {
            if (root != null) {
                answer[0][answerIdx++] = root.idx;
                preOrder(root.left, answer);
                preOrder(root.right, answer);
            }
        }

        public void postOrder(TreeNode root, int[][] answer) {
            if (root != null) {
                postOrder(root.left, answer);
                postOrder(root.right, answer);
                answer[1][answerIdx++] = root.idx;
            }
        }
    }

    private static class TreeNode {
        private int idx;
        private int data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int idx, int data) {
            this.idx = idx;
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private static class Pos implements Comparable<Pos> {
        private int idx;
        private int raw;
        private int col;

        public Pos(int idx, int raw, int col) {
            this.idx = idx;
            this.raw = raw;
            this.col = col;
        }

        @Override
        public int compareTo(Pos target) {
            if (this.raw == target.raw) {
                return this.col - target.col;
            }
            return target.raw - this.raw;
        }
    }
}
