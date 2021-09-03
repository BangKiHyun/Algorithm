package programmers.impl;

import java.util.*;

public class pg_3주차 {
    private static final int EMPTY = 0;
    private static final int EXIST = 1;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private int n;
    private int answer = 0;
    private List<int[][]> pieceList = new ArrayList<>();
    private List<int[][]> emptySpaceList = new ArrayList<>();
    private boolean[] usedPiece;

    public static void main(String[] args) {
        int[][] game_board = {{1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0}};
        int[][] table = {{1, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0}};
        final pg_3주차 task = new pg_3주차();
        System.out.println(task.solution(game_board, table));
    }

    public int solution(int[][] gameBoard, int[][] table) {
        n = gameBoard.length;
        findPieceList(table);
        usedPiece = new boolean[pieceList.size()];
        findEmptySpaceListOfBoard(gameBoard);
        fillBoard();
        return answer;
    }

    private void findEmptySpaceListOfBoard(int[][] gameBoard) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (gameBoard[i][j] == EMPTY) {
                    addEmptySpace(gameBoard, i, j);
                } else {
                    answer++;
                }
            }
        }
    }

    private void addEmptySpace(int[][] gameBoard, int startX, int startY) {
        int[][] space = new int[6][6];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY));
        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            space[curNode.x][curNode.y] = EXIST;
            for (int dir = 0; dir < 4; dir++) {
                int dx = curNode.x + DX[dir];
                int dy = curNode.y + DY[dir];
                if (isNotRange(dx, dy) || gameBoard[dx][dy] == EXIST) continue;
                q.add(new Node(dx, dy));
                gameBoard[dx][dy] = 1;
            }
        }
        emptySpaceList.add(space);
    }

    private void fillBoard() {
        for (int[][] space : emptySpaceList) {
            for (int idx = 0; idx < pieceList.size(); idx++) {
                int[][] piece = pieceList.get(idx);
                for (int count = 0; count < 4; count++) {
                    if (!usedPiece[idx] && isEqual(space, piece)) {
                        usedPiece[idx] = true;
                        answer += findPieceCount(piece);
                    }
                    piece = rotateRight(piece);
                }
            }
        }
    }

    private boolean isEqual(int[][] space, int[][] piece) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (space[i][j] != piece[i][j]) return false;
            }
        }
        return true;
    }

    private int findPieceCount(int[][] piece) {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (piece[i][j] == EXIST) count++;
            }
        }
        return count;
    }

    private int[][] rotateRight(int[][] piece) {
        int[][] newPiece = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                newPiece[i][j] = piece[n - 1 - j][i];
            }
        }
        return newPiece;
    }

    private void findPieceList(int[][] table) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] != EMPTY) {
                    addPiece(table, i, j);
                }
            }
        }
    }

    private void addPiece(int[][] table, int startX, int startY) {
        int[][] piece = new int[6][6];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY));
        while (!q.isEmpty()) {
            final Node curNode = q.poll();
            piece[curNode.x - startX][curNode.y - startY] = EMPTY;
            for (int dir = 0; dir < 4; dir++) {
                int dx = curNode.x + DX[dir];
                int dy = curNode.y + DY[dir];
                if (isNotRange(dx, dy) || table[dx][dy] == EMPTY) continue;
                q.add(new Node(dx, dy));
                table[dx][dy] = 1;
            }
        }
        pieceList.add(piece);
    }

    private boolean isNotRange(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
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
