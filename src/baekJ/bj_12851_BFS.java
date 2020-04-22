package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
//만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
//순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
//
//수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고,
//가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.
public class bj_12851_BFS {
    private static final int INF = 100001;
    private static boolean[] visit = new boolean[INF];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] ans = goBFS(n, k);
        for (int i : ans) {
            System.out.println(i);
        }
    }

    private static int[] goBFS(int me, int finish) {
        int cnt = 0;
        int tempTime = INF;
        Queue<Subin> q = new LinkedList<>();
        visit[me] = true;
        q.offer(new Subin(me, 0));

        while (!q.isEmpty()) {
            Subin cur = q.poll();
            if (cur.pos == finish) {
                tempTime = Math.min(tempTime, cur.time);
                if (tempTime != cur.time) break;
                cnt++;
            }

            for (int i = 0; i < 3; i++) {
                int next_pos = getNextPos(i, cur.pos);
                visit[cur.pos] = true;
                if (isRange(next_pos) && !visit[next_pos]) {
                    q.offer(new Subin(next_pos, cur.time + 1));
                }
            }
        }

        return new int[]{tempTime, cnt};
    }

    private static int getNextPos(int cal, int pos) {
        switch (cal) {
            case 0:
                pos++;
                break;
            case 1:
                pos--;
                break;
            case 2:
                pos *= 2;
                break;
            default:
                break;
        }
        return pos;
    }

    private static boolean isRange(int pos) {
        return pos >= 0 && pos < INF;
    }

    private static class Subin {
        private int pos;
        private int time;

        public Subin(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}
