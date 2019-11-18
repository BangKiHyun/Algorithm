package programmers;

import java.util.Arrays;

public class pg_42860_Greedy {
    private static char[] alpabet;
    private static char[] origin;
    private static int length;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String name = "JAN";
        solution(name);
        System.out.println(min);
    }

    private static int solution(String name) {
        int answer = 0;
        length = name.length();
        alpabet = new char[length];
        origin = name.toCharArray();
        Arrays.fill(alpabet, 'A');

        goDFS(0, 0, 0);
        return answer;
    }

    private static void goDFS(int idx, int depth, int cnt) {
        if (depth == length) {
            min = Math.min(min, cnt);
            return;
        }


        int min_cnt;

        if (origin[idx] == 'A') {
            min_cnt = 1;
        } else if (origin[idx] <= 'M') {
            min_cnt = origin[idx] - alpabet[idx];
        } else {
            min_cnt = 91 - origin[idx];
        }

        if (idx == 0) {
            goDFS(idx + 1, depth + 1, cnt + min_cnt);
            goDFS(length - 1, depth + 1, cnt + min_cnt);
        } else if (idx == length - 1) {
            goDFS(0, depth + 1, cnt + min_cnt);
            goDFS(idx - 1, depth + 1, cnt + min_cnt);
        } else {
            goDFS(idx + 1, depth + 1, cnt + min_cnt);
            goDFS(idx - 1, depth + 1, cnt + min_cnt);
        }

    }

}
