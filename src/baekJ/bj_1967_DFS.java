package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다.
//트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다.
//이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.
//
//이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.
//
//입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오.
public class bj_1967_DFS {
    private static ArrayList<Node>[] lists;
    private static int[] dist;
    private static int maxWeight = 0;
    private static int maxIdx = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        lists = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            lists[from].add(new Node(to, weight));
            lists[to].add(new Node(from, weight));
        }

        goDFS(1, 0);

        maxWeight = 0;
        Arrays.fill(dist, 0);

        goDFS(maxIdx, 0);
        System.out.println(maxWeight);
    }

    private static void goDFS(int idx, int weight) {
        dist[idx] = weight;

        if (dist[idx] > maxWeight) {
            maxWeight = dist[idx];
            maxIdx = idx;
        }

        for (Node next : lists[idx]) {
            if (dist[next.idx] == 0) {
                goDFS(next.idx, next.weight + weight);
            }
        }
    }

    private static class Node {
        private int idx;
        private int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
