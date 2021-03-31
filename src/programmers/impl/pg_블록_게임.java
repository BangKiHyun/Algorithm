package programmers.impl;

import java.util.*;

public class pg_블록_게임 {
    private static int maxSize;
    private static Map<Integer, List<Node>> deletedBlockMap = new HashMap<>();

    private static final int EMPTY = 0;

    public static void main(String[] args) {
        int[][] board =
                {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}};

//                {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 2, 2, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 2, 1, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 2, 1, 0, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
//                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    public static int solution(int[][] board) {
        maxSize = board.length;
        initDeletedBlockList();

        int count = 0;
        for (int raw = 0; raw < maxSize; raw++) {
            for (int col = 0; col < maxSize; col++) {
                int block = board[raw][col];
                if (block != EMPTY) {
                    if (canDelete(raw, col, board)) {
                        count++;
                        col = -1;
                    }
                }
            }
        }
        return count;
    }

    private static boolean canDelete(int x, int y, int[][] board) {
        int targetBlock = board[x][y];
        for (Integer blockIdx : deletedBlockMap.keySet()) {
            final List<Node> deletedNodeList = deletedBlockMap.get(blockIdx);
            List<Node> maybeDeletedNodeList = new ArrayList<>();
            for (Node deleteNode : deletedNodeList) {
                int nx = x + deleteNode.x;
                int ny = y + deleteNode.y;
                if (isRange(nx, ny) && board[nx][ny] == targetBlock) {
                    maybeDeletedNodeList.add(new Node(nx, ny));
                } else {
                    break;
                }
            }
            if (maybeDeletedNodeList.size() == 4) {
                int[] startPosition = getDropPosition(x, y, blockIdx);
                if (isDropBlackBlock(startPosition[0], startPosition[1], targetBlock, board)) {
                    for (Node deleteNode : maybeDeletedNodeList) {
                        board[deleteNode.x][deleteNode.y] = EMPTY;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static int[] getDropPosition(int x, int y, Integer blockIdx) {
        switch (blockIdx) {
            case 1:
                return new int[]{y + 1, y + 2};
            case 2:
                return new int[]{y, y};
            case 3:
                return new int[]{y + 1, y + 1};
            case 4:
                return new int[]{y, y + 1};
            case 5:
                return new int[]{y - 1, y + 1};
        }
        return new int[]{-1, -1};
    }

    private static boolean isDropBlackBlock(int firstY, int secondY, int targetBlock, int[][] board) {
        for (int x = 0; x < maxSize; x++) {
            int block = board[x][firstY];
            if (block != EMPTY) {
                return block == targetBlock;
            }
        }

        if (firstY == secondY) return false;

        for (int x = 0; x < maxSize; x++) {
            int block = board[x][firstY];
            if (block != EMPTY) {
                return block == targetBlock;
            }
        }
        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < maxSize && y < maxSize;
    }

    private static void initDeletedBlockList() {
        int idx = 1;
        List<Node> deletedNodeList = new ArrayList<>();
        deletedNodeList.add(new Node(0, 0));
        deletedNodeList.add(new Node(1, 0));
        deletedNodeList.add(new Node(1, 1));
        deletedNodeList.add(new Node(1, 2));
        deletedBlockMap.put(idx++, deletedNodeList);

        deletedNodeList = new ArrayList<>();
        deletedNodeList.add(new Node(1, 0));
        deletedNodeList.add(new Node(2, 0));
        deletedNodeList.add(new Node(2, -1));
        deletedBlockMap.put(idx++, deletedNodeList);

        deletedNodeList = new ArrayList<>();
        deletedNodeList.add(new Node(0, 0));
        deletedNodeList.add(new Node(1, 0));
        deletedNodeList.add(new Node(2, 0));
        deletedNodeList.add(new Node(2, 1));
        deletedBlockMap.put(idx++, deletedNodeList);

        deletedNodeList = new ArrayList<>();
        deletedNodeList.add(new Node(0, 0));
        deletedNodeList.add(new Node(1, 0));
        deletedNodeList.add(new Node(1, -1));
        deletedNodeList.add(new Node(1, -2));
        deletedBlockMap.put(idx++, deletedNodeList);

        deletedNodeList = new ArrayList<>();
        deletedNodeList.add(new Node(0, 0));
        deletedNodeList.add(new Node(1, 0));
        deletedNodeList.add(new Node(1, -1));
        deletedNodeList.add(new Node(1, 1));
        deletedBlockMap.put(idx++, deletedNodeList);
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

// * .
// * . . .
// * *