package programmers;

import java.util.*;

public class Heap {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        int ScoFirst, ScoSecond;
        int cnt = 0;
        PriorityQueue<Integer> prq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            prq.add(scoville[i]);
        }
        while (!prq.isEmpty()) {
            ScoFirst = prq.poll();
            if (ScoFirst >= K) break;
            if (prq.size() < 1) {
                cnt = -1;
                break;
            }
            cnt++;
            ScoSecond = prq.poll();
            prq.add(ScoFirst + ScoSecond * 2);
        }
        System.out.println(cnt);
    }
}
