package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_12100_DFS_Re {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    private static int n;
    private static int[][] board;
    private static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(0);

        System.out.println(max);
    }

    private static void move(int depth) {
        int[][] copyBoard = new int[n][n];
        copy(copyBoard, board);

        if (depth == 5) {
            searchNumberOfMax(copyBoard);
            return;
        }

        for (int i = 0; i < 4; i++) {
            mergeBoard(copyBoard, i);
            move(depth + 1);
            copy(board, copyBoard);
        }
    }

    private static void copy(int[][] copy, int[][] origin) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = origin[i][j];
            }
        }
    }

    private static void mergeBoard(int[][] copyBoard, int dir) {
        int[][] newBoard = new int[n][n];
        Queue<Integer> q = new LinkedList<>();

        switch (dir) {
            case UP:
                for (int j = 0; j < n; j++) {
                    for (int i = 0; i < n - 1; i++) {
                        if (copyBoard[i][j] != 0) {
                            q.offer(copyBoard[i][j]);
                        }
                    }
                    int x = 0;
                    while (!q.isEmpty()) {
                        int val = q.peek();
                        if (newBoard[x][j] == 0) {
                            newBoard[x][j] = q.poll();
                        } else if (newBoard[x][j] == val) {
                            newBoard[x++][j] += q.poll();
                        } else {
                            newBoard[++x][j] = q.poll();
                        }
                    }
                }
                break;
            case DOWN:
                for (int j = 0; j < n; j++) {
                    for (int i = n - 1; i > 0; i--) {
                        if (copyBoard[i][j] != 0) {
                            q.offer(copyBoard[i][j]);
                        }
                    }
                    int x = n - 1;
                    while (!q.isEmpty()) {
                        int val = q.peek();
                        if (newBoard[x][j] == 0) {
                            newBoard[x][j] = q.poll();
                        } else if (newBoard[x][j] == val) {
                            newBoard[x--][j] += q.poll();
                        } else {
                            newBoard[--x][j] = q.poll();
                        }
                    }
                }
                break;
            case LEFT:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - 1; j++) {
                        if (copyBoard[i][j] != 0) {
                            q.offer(copyBoard[i][j]);
                        }
                    }
                    int y = 0;
                    while (!q.isEmpty()) {
                        int val = q.peek();
                        if (newBoard[i][y] == 0) {
                            newBoard[i][y] = q.poll();
                        } else if (newBoard[i][y] == val) {
                            newBoard[i][y++] += q.poll();
                        } else {
                            newBoard[i][++y] = q.poll();
                        }
                    }
                }
            case RIGHT:
                for (int i = 0; i < n; i++) {
                    for (int j = n - 1; j > 0; j--) {
                        if (copyBoard[i][j] != 0) {
                            q.offer(copyBoard[i][j]);
                        }
                    }
                    int y = n - 1;
                    while (!q.isEmpty()) {
                        int val = q.peek();
                        if (newBoard[i][y] == 0) {
                            newBoard[i][y] = q.poll();
                        } else if (newBoard[i][y] == val) {
                            newBoard[i][y--] += q.poll();
                        } else {
                            newBoard[i][--y] = q.poll();
                        }
                    }
                }
                break;
            default:
                break;
        }
        copy(board, newBoard);
    }

    private static void searchNumberOfMax(int[][] board) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
    }
}