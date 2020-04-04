package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//동혁이는 친구들과 함께 여행을 가려고 한다.
//한국에는 도시가 N개 있고 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다.
//동혁이의 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지 알아보자.
//물론 중간에 다른 도시를 경유해서 여행을 할 수도 있다. 예를 들어 도시가 5개 있고, A-B, B-C, A-D, B-D, E-A의 길이 있고,
//동혁이의 여행 계획이 E C B C D 라면 E-A-B-C-B-C-B-D라는 여행경로를 통해 목적을 달성할 수 있다.
//
//도시들의 개수와 도시들 간의 연결 여부가 주어져 있고, 동혁이의 여행 계획에 속한 도시들이 순서대로 주어졌을 때(중복 가능)
//가능한지 여부를 판별하는 프로그램을 작성하시오.
public class bj_1976_DisjointSet {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    unionParent(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int root = Integer.parseInt(st.nextToken());
        while (st.hasMoreElements()) {
            int node = Integer.parseInt(st.nextToken());
            if (!findParent(root, node)) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static void unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    private static int getParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    private static boolean findParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        return a == b;
    }
}
