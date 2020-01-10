package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_1038_브루트포스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Long> queue = new LinkedList<>();
        if (n <= 10) {
            System.out.println(n);
            return;
        }
        if (n > 1022) {
            System.out.println(-1);
            return;
        }

        for (long i = 0; i < 10; i++) queue.add(i);
        int cnt = 9;
        while (cnt < n) {
            long before = queue.poll();
            long temp = before % 10;
            for (int i = 0; i < temp; i++) {
                long next = before * 10 + i;
                queue.add(next);
                cnt++;
                if (cnt == n) {
                    System.out.println(((LinkedList<Long>) queue).pollLast());
                    return;
                }
            }
        }
    }
}
