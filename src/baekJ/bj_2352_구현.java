package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//n개의 포트가 다른 n개의 포트와 어떻게 연결되어야 하는지가 주어졌을 때,
//연결선이 서로 꼬이지(겹치지, 교차하지) 않도록 하면서 최대 몇 개까지 연결할 수 있는지를 알아내는 프로그램을 작성하시오
public class bj_2352_구현 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //첫째 줄에 정수 n(1 ≤ n ≤ 40,000)이 주어진다.
        int n = Integer.parseInt(br.readLine());

        //다음 줄에는 차례로 1번 포트와 연결되어야 하는 포트 번호, 2번 포트와 연결되어야 하는 포트 번호, …, n번 포트와 연결되어야 하는 포트 번호가 주어진다.
        // 이 수들은 1 이상 n 이하이며 서로 같은 수는 없다고 가정하자.
        int[] port = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = 1;
        while (st.hasMoreElements()) {
            int to = Integer.parseInt(st.nextToken());
            port[from++] = to;
        }

        boolean[] visit = new boolean[n + 1];
        int connectedCnt = 0;
        for (int i = 1; i <= n; i++) {
            cutConnection(i, port[i], port, visit);
            int cnt = connectedPort(port, visit, 1);
            Arrays.fill(visit, false);
            connectedCnt = Math.max(connectedCnt, cnt);
        }

        System.out.println(connectedCnt);
    }

    private static int connectedPort(int[] port, boolean[] visit, int cnt) {
        for (int i = 1; i < port.length; i++) {
            if (!visit[i]) {
                cutConnection(i, port[i], port, visit);
                cnt++;
            }
        }
        return cnt;
    }

    private static void cutConnection(int current, int to, int[] port, boolean[] visit) {
        for (int from = 1; from < port.length; from++) {
            if (from >= current && port[from] <= to) {
                visit[from] = true;
            } else if (from < current && port[from] > to) {
                visit[from] = true;
            }
        }
    }
}
