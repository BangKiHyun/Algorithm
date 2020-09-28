package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//수행해야 할 작업 N개 (3 ≤ N ≤ 10000)가 있다.
//각각의 작업마다 걸리는 시간(1 ≤ 시간 ≤ 100)이 정수로 주어진다.
//
//몇몇 작업들 사이에는 선행 관계라는 게 있어서, 어떤 작업을 수행하기 위해 반드시 먼저 완료되어야 할 작업들이 있다.
//이 작업들은 번호가 아주 예쁘게 매겨져 있어서, K번 작업에 대해 선행 관계에 있는(즉, K번 작업을 시작하기 전에 반드시 먼저 완료되어야 하는) 작업들의 번호는 모두 1 이상 (K-1) 이하이다.
//작업들 중에는, 그것에 대해 선행 관계에 있는 작업이 하나도 없는 작업이 반드시 하나 이상 존재한다. (1번 작업이 항상 그러하다)
//
//모든 작업을 완료하기 위해 필요한 최소 시간을 구하여라. 물론, 서로 선행 관계가 없는 작업들은 동시에 수행 가능하다.
public class bj_2056_Topology {
    private static int n;
    private static int[] cost;
    private static int accumulationTime[];
    private static int[] inDegree;
    private static List<Integer>[] works;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        cost = new int[n + 1];
        accumulationTime = new int[n + 1];
        inDegree = new int[n + 1];
        works = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            works[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            int preWorkCnt = Integer.parseInt(st.nextToken());

            while (st.hasMoreElements()) {
                int work = Integer.parseInt(st.nextToken());
                inDegree[i]++;
                works[work].add(i);
            }
        }

        goTopology();
        System.out.println(getAnswer());
    }

    private static void goTopology() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                accumulationTime[i] = cost[i];
            }
        }

        while (!q.isEmpty()) {
            int curIdx = q.poll();

            for (int nextIdx : works[curIdx]) {
                accumulationTime[nextIdx] = Math.max(accumulationTime[nextIdx], accumulationTime[curIdx] + cost[nextIdx]);
                if (--inDegree[nextIdx] == 0) {
                    q.offer(nextIdx);
                }
            }
        }
    }

    private static int getAnswer() {
        int max = 0;
        for (int time : accumulationTime) {
            max = Math.max(max, time);
        }
        return max;
    }
}
