package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//민오는 1번부터 N번까지 총 N개의 문제로 되어 있는 문제집을 풀려고 한다. 문제는 난이도 순서로 출제되어 있다.
//즉 1번 문제가 가장 쉬운 문제이고 N번 문제가 가장 어려운 문제가 된다.
//
//어떤 문제부터 풀까 고민하면서 문제를 훑어보던 민오는, 몇몇 문제들 사이에는 '먼저 푸는 것이 좋은 문제'가 있다는 것을 알게 되었다.
//예를 들어 1번 문제를 풀고 나면 4번 문제가 쉽게 풀린다거나 하는 식이다. 민오는 다음의 세 가지 조건에 따라 문제를 풀 순서를 정하기로 하였다.
//
//N개의 문제는 모두 풀어야 한다.
//먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
//가능하면 쉬운 문제부터 풀어야 한다.
public class bj_1766_Topology {
    private static ArrayList<Integer>[] lists;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        lists = new ArrayList[n + 1];
        inDegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            lists[from].add(to);
            inDegree[to]++;
        }

        goTopology(n);
    }

    private static void goTopology(int n) {
        Queue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            for (int next : lists[cur]) {
                if (--inDegree[next] == 0) {
                    pq.offer(next);
                }
            }

            System.out.print(cur + " ");
        }
    }
}

/*
5 4
4 1
5 1
2 5
3 5
*/