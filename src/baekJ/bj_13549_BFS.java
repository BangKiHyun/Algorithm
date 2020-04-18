package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
//수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
//순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
//
//수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
public class bj_13549_BFS {
    private static final int maxRange = 100001;
    private static boolean visit[] = new boolean[maxRange];

    public static void main(String[] args) throws IOException {
        //첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int ans = findBrother(n, k);
        System.out.println(ans);
    }

    private static int findBrother(int me, int brother) {
        Queue<Subin> q = new LinkedList<>();
        q.offer(new Subin(me, 0));
        visit[me] = true;

        while (!q.isEmpty()) {
            Subin cur = q.poll();
            if (cur.pos == brother) {
                return cur.time;
            }

            int next_pos = cur.pos * 2;
            while (isValue(next_pos)) {
                visit[next_pos] = true;
                q.offer(new Subin(next_pos, cur.time));
                next_pos *= 2;
            }

            for (int i = 0; i < 2; i++) {
                next_pos = -1;
                switch (i) {
                    case 0:
                        next_pos = cur.pos - 1;
                        break;
                    case 1:
                        next_pos = cur.pos + 1;
                        break;
                }

                if (isValue(next_pos)) {
                    visit[next_pos] = true;
                    q.offer(new Subin(next_pos, cur.time + 1));

                }
            }
        }
        return -1;
    }

    private static boolean isValue(int pos) {
        return pos >= 0 && pos < maxRange && !visit[pos];
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
