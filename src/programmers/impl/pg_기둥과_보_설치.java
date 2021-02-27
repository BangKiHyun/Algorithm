package programmers.impl;

public class pg_기둥과_보_설치 {

    private static final int PILLAR = 0;
    private static final int CLOTH = 1;
    private static boolean[][][] board;

    private static int buildCount = 0;

    public static void main(String[] args) {
        int n = 5;

        // {x, y, a, b}
        // x, y 좌표
        // a: 0은 기둥, 1은 보
        // b: 0은 삭제, 1은 설치
        int[][] build_frame = {{1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1,},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}};

        for (int[] answer : solution(n, build_frame)) {
            System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
        }
    }

    public static int[][] solution(int n, int[][] build_frame) {
        board = new boolean[n + 1][n + 1][2];
        for (int[] build : build_frame) {
            int col = build[0];
            int raw = build[1];
            int structureType = build[2];
            int buildStatus = build[3];

            if (structureType == PILLAR) {
                repairPillar(raw, col, buildStatus);
            } else {
                repairCloth(raw, col, buildStatus);
            }
        }

        int[][] answer = new int[buildCount][3];
        int answerIdx = 0;
        for (int col = 0; col <= n; col++) {
            for (int raw = 0; raw <= n; raw++) {
                if (board[raw][col][PILLAR]) {
                    answer[answerIdx][0] = col;
                    answer[answerIdx][1] = raw;
                    answer[answerIdx++][2] = PILLAR;
                    continue;
                }
                if (board[raw][col][CLOTH]) {
                    answer[answerIdx][0] = col;
                    answer[answerIdx][1] = raw;
                    answer[answerIdx++][2] = CLOTH;
                }
            }
        }
        return answer;
    }

    private static void repairPillar(int raw, int col, int buildStatus) {
        if (buildStatus == 0) {
            board[raw][col][PILLAR] = false;
            buildCount--;
            if (!canRemovePillar(raw, col)) {
                board[raw][col][PILLAR] = true;
                buildCount++;
            }
        } else {
            if (canBuildPillar(raw, col)) {
                board[raw][col][PILLAR] = true;
                buildCount++;
            }
        }
    }

    // 바닥 위, 기둥 위, 보의 한 쪽 위
    private static boolean canBuildPillar(int raw, int col) {
        return raw == 0 || isPillar(raw - 1, col) || isCloth(raw, col) || isCloth(raw, col - 1);
    }

    private static boolean canRemovePillar(int raw, int col) {
        if ((isPillar(raw + 1, col) && !canBuildPillar(raw + 1, col))
                || (isCloth(raw + 1, col) && !canBuildCloth(raw + 1, col))
                || (isCloth(raw + 1, col - 1) && !canBuildCloth(raw + 1, col - 1))) {
            return false;
        }
        return true;
    }

    private static void repairCloth(int raw, int col, int buildStatus) {
        if (buildStatus == 0) {
            board[raw][col][CLOTH] = false;
            buildCount--;
            if (!canRemoveCloth(raw, col)) {
                board[raw][col][CLOTH] = true;
                buildCount++;
            }
        } else {
            if (canBuildCloth(raw, col)) {
                board[raw][col + 1][CLOTH] = true;
                buildCount++;
            }
        }
    }

    // 기둥 아래, 양 옆에 보
    private static boolean canBuildCloth(int raw, int col) {
        return isPillar(raw - 1, col) || isPillar(raw - 1, col + 1)
                || (isCloth(raw, col - 1) && isCloth(raw, col + 1));
    }

    private static boolean canRemoveCloth(int raw, int col) {
        if ((isPillar(raw, col) && !canBuildPillar(raw, col))
                || (isPillar(raw, col + 1) && !canBuildPillar(raw, col + 1))
                || (isCloth(raw, col - 1) && !canBuildCloth(raw, col - 1))
                || (isCloth(raw, col + 1) && !canBuildCloth(raw, col + 1))) {
            return false;
        }
        return true;
    }

    private static boolean isPillar(int raw, int col) {
        if (raw < 0 || raw > board.length || col < 0 || col > board.length) {
            return false;
        }

        return board[raw][col][PILLAR];
    }

    private static boolean isCloth(int raw, int col) {
        if (raw < 0 || raw > board.length || col < 0 || col > board.length) {
            return false;
        }

        return board[raw][col][CLOTH];
    }
}
