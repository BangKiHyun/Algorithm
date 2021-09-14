package programmers.impl;

import java.util.ArrayList;
import java.util.List;

public class pg_행렬_테두리_회전하기_Re {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4},
                {3, 3, 6, 6},
                {5, 1, 6, 3}};
        for (int ans : solution(rows, columns, queries)) {
            System.out.print(ans + " ");
        }
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] board = new int[rows + 1][columns + 1];
        int number = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                board[i][j] = number++;
            }
        }
        List<Integer> answerList = new ArrayList<>();
        for (int[] query : queries) {
            int startX = query[0];
            int startY = query[1];
            int endX = query[2];
            int endY = query[3];
            int dir = 0;
            int value = board[startX][startY];
            int minValue = Integer.MAX_VALUE;
            while (dir < 4) {
                int nx = startX + dx[dir];
                int ny = startY + dy[dir];
                if (nx >= query[0] && ny >= query[1] && nx <= endX && ny <= endY) {
                    minValue = Math.min(minValue, board[nx][ny]);
                    board[startX][startY] = board[nx][ny];
                    startX = nx;
                    startY = ny;
                } else dir++;
            }
            board[query[0]][query[1] + 1] = value;
            minValue = Math.min(minValue, value);
            answerList.add(minValue);
        }
        int[] answer = new int[answerList.size()];
        int idx = 0;
        for (int value : answerList) {
            answer[idx++] = value;
        }
        return answer;
    }
}
