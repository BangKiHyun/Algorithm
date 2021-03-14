package programmers.impl;

import java.util.*;

public class pg_카드_짝_맞추기 {
    private static final int MAX_LENGTH = 4;
    private static final int EMPTY = 0;
    private static final int ENTER_COUNT = 2;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static List<Integer> cardTypeList = new ArrayList<>();
    private static boolean[] visitCardType;
    private static Deque<Integer> priorityOfcards = new LinkedList<>();
    private static int minCountOfFlip = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] board = {{3, 0, 0, 2},
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {2, 0, 0, 3}};
        int r = 1;
        int c = 0;

        System.out.println(solution(board, r, c));
    }

    public static int solution(int[][] board, int r, int c) {
        initCarTypeList(board);
        visitCardType = new boolean[cardTypeList.size()];
        flipCard(board, r, c, 0);
        return minCountOfFlip;
    }

    private static void flipCard(int[][] board, int startRaw, int startCol, int depth) {
        if (depth == cardTypeList.size()) {
            final int countOfFlip = findMinCountOfFlip(board, startRaw, startCol);
            minCountOfFlip = Math.min(minCountOfFlip, countOfFlip);
            return;
        }

        for (int cardTypeIdx = 0; cardTypeIdx < cardTypeList.size(); cardTypeIdx++) {
            if (!visitCardType[cardTypeIdx]) {
                visitCardType[cardTypeIdx] = true;
                priorityOfcards.offer(cardTypeList.get(cardTypeIdx));
                flipCard(board, startRaw, startCol, depth + 1);
                priorityOfcards.remove(cardTypeList.get(cardTypeIdx));
                visitCardType[cardTypeIdx] = false;
            }
        }
    }

    private static int findMinCountOfFlip(int[][] board, int rawIdx, int colIdx) {
        int countOfFlip = 0;
        int[][] newBoard = copyBoard(board);
        for (int cardType : priorityOfcards) {
            final Node nextNode = getNextNode(newBoard, cardType, rawIdx, colIdx);
            countOfFlip += nextNode.count;
            rawIdx = nextNode.raw;
            colIdx = nextNode.col;
        }
        return countOfFlip + ENTER_COUNT * cardTypeList.size();
    }

    private static Node getNextNode(int[][] newBoard, int targetCardType, int startRaw, int startCol) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visitBoard = new boolean[MAX_LENGTH][MAX_LENGTH];
        queue.add(new Node(startRaw, startCol, 0));
        visitBoard[startRaw][startCol] = true;

        int numberOfMatch = 0;
        while (!queue.isEmpty()) {
            final Node curNode = queue.poll();
            int raw = curNode.raw;
            int col = curNode.col;
            if (newBoard[raw][col] == targetCardType) {
                newBoard[raw][col] = EMPTY;
                if (++numberOfMatch == 2) {
                    return curNode;
                }
                visitBoard = new boolean[MAX_LENGTH][MAX_LENGTH];
                queue.clear();
                visitBoard[raw][col] = true;
            }

            for (int dirIdx = 0; dirIdx < 4; dirIdx++) {
                int nextRawOfOneMove = raw + DX[dirIdx];
                int nextColOfOneMove = col + DY[dirIdx];
                if (isRange(nextRawOfOneMove, nextColOfOneMove) && !visitBoard[nextRawOfOneMove][nextColOfOneMove]) {
                    visitBoard[nextRawOfOneMove][nextColOfOneMove] = true;
                    queue.add(new Node(nextRawOfOneMove, nextColOfOneMove, curNode.count + 1));
                }

                int nextRawOfEndMove = getNextSpaceOfRaw(nextRawOfOneMove, col, dirIdx, newBoard);
                int nextColOfEndMove = getNextSpaceOfCol(raw, nextColOfOneMove, dirIdx, newBoard);
                if (isRange(nextRawOfEndMove, nextColOfEndMove) && !visitBoard[nextRawOfEndMove][nextColOfEndMove]) {
                    visitBoard[nextRawOfEndMove][nextColOfEndMove] = true;
                    queue.add(new Node(nextRawOfEndMove, nextColOfEndMove, curNode.count + 1));
                }
            }
        }
        return null;
    }

    private static void initCarTypeList(int[][] board) {
        for (int rawIdx = 0; rawIdx < MAX_LENGTH; rawIdx++) {
            for (int colIdx = 0; colIdx < MAX_LENGTH; colIdx++) {
                int cardType = board[rawIdx][colIdx];
                if (cardType == EMPTY || cardTypeList.contains(cardType)) continue;
                cardTypeList.add(cardType);
            }
        }
    }

    private static boolean isRange(int raw, int col) {
        return raw >= 0 && col >= 0 && raw < MAX_LENGTH && col < MAX_LENGTH;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[MAX_LENGTH][MAX_LENGTH];
        for (int rawIdx = 0; rawIdx < MAX_LENGTH; rawIdx++) {
            System.arraycopy(board[rawIdx], 0, newBoard[rawIdx], 0, MAX_LENGTH);
        }
        return newBoard;
    }

    private static int getNextSpaceOfRaw(int raw, int col, int dir, int[][] board) {
        if (dir == 2 || dir == 3) return raw;
        if (dir == 0) {
            return getNextSpaceOfUpRaw(raw, col, dir, board);
        }
        return getNextSpaceOfDownRaw(raw, col, dir, board);
    }

    private static int getNextSpaceOfUpRaw(int raw, int col, int dir, int[][] board) {
        for (int rawIdx = raw; rawIdx >= 0; rawIdx += DX[dir]) {
            if (board[rawIdx][col] != EMPTY) {
                return rawIdx;
            }
        }
        return 0;
    }

    private static int getNextSpaceOfDownRaw(int raw, int col, int dir, int[][] board) {
        for (int rawIdx = raw; rawIdx < MAX_LENGTH; rawIdx += DX[dir]) {
            if (board[rawIdx][col] != EMPTY) {
                return rawIdx;
            }
        }
        return MAX_LENGTH - 1;
    }

    private static int getNextSpaceOfCol(int raw, int col, int dir, int[][] board) {
        if (dir == 0 || dir == 1) return col;
        if (dir == 2) {
            return getNextSpaceOfLeftCol(raw, col, dir, board);
        }
        return getNextSpaceOfRightCol(raw, col, dir, board);
    }

    private static int getNextSpaceOfLeftCol(int raw, int col, int dir, int[][] board) {
        for (int colIdx = col; colIdx >= 0; colIdx += DY[dir]) {
            if (board[raw][colIdx] != EMPTY) {
                return colIdx;
            }
        }
        return 0;
    }

    private static int getNextSpaceOfRightCol(int raw, int col, int dir, int[][] board) {
        for (int colIdx = col; colIdx < MAX_LENGTH; colIdx += DY[dir]) {
            if (board[raw][colIdx] != EMPTY) {
                return colIdx;
            }
        }
        return MAX_LENGTH - 1;
    }

    private static class Node {
        private int raw;
        private int col;
        private int count;

        public Node(int raw, int col, int count) {
            this.raw = raw;
            this.col = col;
            this.count = count;
        }
    }
}
