package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_2309_브루트포스 {
    private static final int LENGTH = 9;
    private static final int MAX = 7;
    private static final int ANSWER = 100;
    private static boolean[] visit = new boolean[LENGTH];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] candidator = new int[LENGTH];

        //아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다. 주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러 가지인 경우에는 아무거나 출력한다.
        for (int i = 0; i < LENGTH; i++) {
            int height = Integer.parseInt(br.readLine());
            candidator[i] = height;
        }

        Arrays.sort(candidator);

        for (int i = 0; i < 4; i++) {
            if (!visit[i]) {
                visit[i] = true;
                if (permucomb(i + 1, candidator[i], 1, candidator)) {
                    break;
                }
                visit[i] = false;
            }
        }

        int[] answer = getAnswer(candidator);

        for (int i : answer) {
            System.out.println(i);
        }
    }

    private static boolean permucomb(int start, int height, int depth, int[] candidator) {
        if (depth == MAX && height == ANSWER) {
            return true;
        }

        for (int i = start; i < LENGTH; i++) {
            if (!visit[start]) {
                visit[i] = true;
                if (permucomb(i + 1, height + candidator[i], depth + 1, candidator)) {
                    return true;
                }
                visit[i] = false;
            }
        }
        return false;
    }

    private static int[] getAnswer(int[] candidator) {
        int[] answer = new int[MAX];
        int idx = 0;
        for (int i = 0; i < LENGTH; i++) {
            if (visit[i]) {
                answer[idx] = candidator[i];
                idx++;
            }
        }
        return answer;
    }
}
