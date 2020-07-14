package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_5014_BFS_Re {
    private static int[] button = new int[2];
    private static int floor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        floor = Integer.parseInt(input[0]);
        int start = Integer.parseInt(input[1]);
        int finish = Integer.parseInt(input[2]);
        button[0] = Integer.parseInt(input[3]);
        button[1] = Integer.parseInt(input[4]) * -1;

        System.out.println(getAnswer(start, finish));
    }

    private static String getAnswer(final int start, final int finish) {
        int[] count = new int[floor + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int curPos = q.poll();

            if (curPos == finish) {
                return String.valueOf(count[curPos]);
            }

            for (int i = 0; i < 2; i++) {
                if (button[i] == 0) continue;

                int nextPos = curPos + button[i];

                if (isValid(nextPos, count)) {
                    count[nextPos] = count[curPos] + 1;
                    q.add(nextPos);
                }
            }
        }

        return "use the stairs";
    }

    private static boolean isValid(final int nextPos, final int[] count) {
        return nextPos > 0 && nextPos <= floor && count[nextPos] == 0;
    }
}
