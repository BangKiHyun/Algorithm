package baekJ.queue.priority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.System.in;

public class bj_1927_최소힙 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        Queue<Long> prq = new PriorityQueue<>();
        for(int idx = 0; idx < n; idx++){
            long num = Long.parseLong(new StringTokenizer(br.readLine()).nextToken());

            if(num == 0) {
                if(prq.isEmpty()) System.out.println(num);
                else System.out.println(prq.poll());
            }else {
                prq.add(num);
            }
        }
    }
}
