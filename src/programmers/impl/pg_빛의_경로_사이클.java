package programmers.impl;

import java.util.ArrayList;

public class pg_빛의_경로_사이클 {
    private static final int[] DX = {-1, 0, 1, 0};
    private static final int[] DY = {0, -1, 0, 1};

    private static int rowN, colN;
    private static boolean[][][] visited;

    public static void main(String[] args) {
        final pg_빛의_경로_사이클 task = new pg_빛의_경로_사이클();
        String[] grid = {"SL", "LR"};
        for (int answer : task.solution(grid)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(String[] grid) {
        final ArrayList<Integer> answerList = new ArrayList<>();
        rowN = grid.length;
        colN = grid[0].length();
        visited = new boolean[rowN][colN][4];

        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (!visited[i][j][dir]) {
                        answerList.add(light(grid, i, j, dir));
                    }
                }
            }
        }

        return answerList.stream()
                .sorted()
                .mapToInt(answer -> answer)
                .toArray();
    }

    private int light(String[] grid, int r, int c, int dir) {
        int cnt = 0;
        while (true) {
            if (visited[r][c][dir]) break;

            cnt++;
            visited[r][c][dir] = true;
            char nextDir = grid[r].charAt(c);
            if (nextDir == 'L') {
                dir = (dir + 3) % 4;
            } else if (nextDir == 'R') {
                dir = (dir + 1) % 4;
            }

            r = (r + DX[dir] + rowN) % rowN;
            c = (c + DY[dir] + colN) % colN;
        }
        return cnt;
    }
}
