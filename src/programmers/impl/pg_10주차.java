package programmers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class pg_10주차 {

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4},
                {-2, -1, 4},
                {0, -1, 1},
                {5, -8, -12},
                {5, 8, 12}};
        final pg_10주차 task = new pg_10주차();
        for (String answer : task.solution(line)) {
            System.out.println(answer);
        }
    }

    public String[] solution(int[][] line) {
        long startX = Long.MAX_VALUE;
        long endX = Long.MIN_VALUE;
        long startY = Long.MAX_VALUE;
        long endY = Long.MIN_VALUE;

        List<Pos> posList = new ArrayList<>();
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long denominator = (line[i][0] * line[j][1] - (line[i][1] * line[j][0]));
                if (denominator == 0) continue;

                double x = (double) ((line[i][1] * line[j][2]) - (line[i][2] * line[j][1])) / denominator;
                double y = (double) ((line[i][2] * line[j][0]) - (line[i][0] * line[j][2])) / denominator;
                if ((x % 1 != 0) || (y % 1 != 0)) continue;

                Pos pos = new Pos((long) x, (long) y);
                if (!posList.contains(pos)) posList.add(pos);

                startX = Math.min(startX, (long) x);
                endX = Math.max(endX, (long) x);
                startY = Math.min(startY, (long) y);
                endY = Math.max(endY, (long) y);
            }
        }

        List<String> board = new ArrayList<>();
        for (long i = startY; i <= endY; i++) {
            StringBuilder sb = new StringBuilder();
            for (long j = startX; j <= endX; j++) {
                sb.append(".");
            }
            board.add(sb.toString());
        }

        for (Pos pos : posList) {
            StringBuilder singleLine = new StringBuilder(board.get((int) Math.abs(pos.y - endY)));
            singleLine.setCharAt((int) Math.abs(pos.x - startX), '*');
            board.set((int) Math.abs(pos.y - endY), singleLine.toString());
        }

        String[] answer = new String[board.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = board.get(i);
        }

        return answer;
    }

    private static class Pos {
        private long x;
        private long y;

        public Pos(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pos)) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                    y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
