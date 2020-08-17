package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj_11866_Queue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()) - 1;

        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cnt = 0;
        while (!q.isEmpty()) {
            if (cnt == k) {
                sb.append(q.poll());
                cnt = 0;

                if (q.size() != 0) {
                    sb.append(", ");
                }

            } else {
                int temp = q.poll();
                q.offerLast(temp);
                cnt++;
            }
        }

        sb.append(">");
        System.out.println(sb);
    }
}
//1 2(dead) 3(dead) 4 5 6(dead) 7
//3 6 2