package baekJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class bj_1715_카드정렬하기 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> prq = new PriorityQueue<>();
        for (int cnt = 0; cnt < N; cnt++) {
            prq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (N - 1 > 0) {
            N--;
            final int firstNum = prq.poll();
            final int secondNum = prq.poll();
            final int sum = firstNum + secondNum;
            prq.offer(sum);
            answer += sum;
        }
        System.out.println(answer);
    }
}
