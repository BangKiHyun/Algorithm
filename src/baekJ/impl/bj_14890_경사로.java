package baekJ.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14890_경사로 {
    private static int n, l;
    private static int[][] board;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int raw = 0; raw < n; raw++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < n; col++) {
                board[raw][col] = Integer.parseInt(st.nextToken());
            }
        }
        findCanMovePath();
        System.out.println(answer);
    }

    private static void findCanMovePath() {
        findCanMovePathOfRaw();
        findCanMovePathOfCol();
    }

    private static void findCanMovePathOfRaw() {
        for (int col = 0; col < n; col++) {
            if (isCanMovePathOfRaw(col)) {
                System.out.println("Col: " + col);
                answer++;
            }
        }
    }

    private static boolean isCanMovePathOfRaw(int col) {
        int preHeight = board[0][col];
        int preIdx = 0;
        boolean[] visit = new boolean[n];
        for (int raw = 1; raw < n; raw++) {
            int curHeight = board[raw][col];
            if (Math.abs(preHeight - curHeight) > 1) return false;
            if (preHeight < curHeight) {
                if (raw - preIdx < l) return false;
                preIdx = raw;
                preHeight = curHeight;
            } else if (preHeight > curHeight) {
                if (getNextHeightEqualsCountOfRaw(raw, col, curHeight, visit) < l) return false;
                preIdx = raw;
                preHeight = curHeight;
                for (int idx = raw; idx < raw + l; idx++) {
                    visit[idx] = true;
                }
            }
        }
        return true;
    }

    private static int getNextHeightEqualsCountOfRaw(int raw, int colIdx, int curHeight, boolean[] visit) {
        int equalsCount = 0;
        for (int rawIdx = raw; rawIdx < n; rawIdx++) {
            if (board[rawIdx][colIdx] == curHeight && !visit[rawIdx]) equalsCount++;
            else break;
        }
        return equalsCount;
    }

    private static void findCanMovePathOfCol() {
        for (int raw = 0; raw < n; raw++) {
            if (isCanMovePathOfCol(raw)) {
                System.out.println("Raw: " + raw);
                answer++;
            }
        }
    }

    private static boolean isCanMovePathOfCol(int raw) {
        int preHeight = board[raw][0];
        int preIdx = 0;
        boolean[] visit = new boolean[n];
        for (int col = 1; col < n; col++) {
            int curHeight = board[raw][col];
            if (Math.abs(preHeight - curHeight) > 1) return false;
            if (preHeight < curHeight) {
                if (col - preIdx < l) return false;
                preIdx = col;
                preHeight = curHeight;
            } else if (preHeight > curHeight) {
                if (getNextHeightEqualsCountOfCol(raw, col, curHeight, visit) < l) return false;
                preIdx = col;
                preHeight = curHeight;
                for (int idx = col; idx < col + l; idx++) {
                    visit[idx] = true;
                }
            }
        }
        return true;
    }

    private static int getNextHeightEqualsCountOfCol(int rawIdx, int col, int curHeight, boolean[] visit) {
        int equalsCount = 0;
        for (int colIdx = col; colIdx < n; colIdx++) {
            if (board[rawIdx][colIdx] == curHeight && !visit[colIdx]) equalsCount++;
            else break;
        }
        return equalsCount;
    }
}
