package programmers.bfs;

import java.util.*;

public class pg_거리두기_확인하기 {
    private final int n = 5;
    private final int[] dx = {-1, 1, 0, 0};
    private final int[] dy = {0, 0, -1, 1};
    private int[][] separationBoard;

    public static void main(String[] args) {
        final pg_거리두기_확인하기 task = new pg_거리두기_확인하기();
        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        for (int answer : task.solution(places)) {
            System.out.print(answer + " ");
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int answerIdx = 0;

        for (String[] place : places) {
            String[][] board = new String[n][n];
            separationBoard = new int[n][n];
            initBoard(place, board);
            List<Pos> posList = new ArrayList<>();
            int boardIdx = 1;
            for (int raw = 0; raw < n; raw++) {
                for (int col = 0; col < n; col++) {
                    if (board[raw][col].equals("P") && separationBoard[raw][col] == 0) {
                        bfs(posList, board, raw, col, boardIdx++);
                    }
                }
            }
            if (posList.size() < 2) {
                answer[answerIdx++] = 1;
                continue;
            }
            posList.sort((standard, compare)
                    -> Math.abs(standard.x - compare.x) + Math.abs(standard.y - compare.y));
            boolean isCorrect = true;
            for (int idx = 0; idx < posList.size() - 1; idx++) {
                if (!isCorrect) break;
                final Pos firstPos = posList.get(idx);
                for (int nextIdx = idx + 1; nextIdx < posList.size(); nextIdx++) {
                    final Pos secondPos = posList.get(nextIdx);
                    int size = Math.abs(firstPos.x - secondPos.x) + Math.abs(firstPos.y - secondPos.y);
                    if (size > 2) break;
                    if (separationBoard[firstPos.x][firstPos.y] == separationBoard[secondPos.x][secondPos.y]) {
                        isCorrect = false;
                        break;
                    }
                }
            }
            if (!isCorrect) {
                answer[answerIdx++] = 0;
            } else answer[answerIdx++] = 1;
        }

        return answer;
    }

    private void initBoard(String[] place, String[][] board) {
        for (int raw = 0; raw < n; raw++) {
            String[] keyword = place[raw].split("");
            for (int col = 0; col < n; col++) {
                board[raw][col] = keyword[col];
            }
        }
    }

    private void bfs(List<Pos> posList, String[][] places, int startRaw, int startCol, int boardIdx) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(startRaw, startCol));
        separationBoard[startRaw][startCol] = boardIdx;
        while (!q.isEmpty()) {
            final Pos curPos = q.poll();
            if (places[curPos.x][curPos.y].equals("P")) posList.add(curPos);

            for (int dir = 0; dir < 4; dir++) {
                int nx = dx[dir] + curPos.x;
                int ny = dy[dir] + curPos.y;
                if (isRange(nx, ny) && !places[nx][ny].equals("X") && separationBoard[nx][ny] == 0) {
                    separationBoard[nx][ny] = boardIdx;
                    q.add(new Pos(nx, ny));
                }
            }

        }
    }

    private boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    private class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
