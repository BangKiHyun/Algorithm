package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다.
//우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.
//
//어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
//
//친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.
public class bj_4195_DisjointSet {
    private static int[] parent;
    private static Map<String, Integer> map;
    private static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            parent = new int[n * 2];
            cnt = new int[n * 2];
            map = new HashMap<>();

            for (int i = 0; i < n * 2; i++) {
                parent[i] = i;
                cnt[i] = 1;
            }

            StringTokenizer st;
            String first, second;
            int idx = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                first = st.nextToken();
                second = st.nextToken();

                if (!map.containsKey(first)) map.put(first, idx++);
                if (!map.containsKey(second)) map.put(second, idx++);

                int a = map.get(first);
                int b = map.get(second);

                int root = getParent(a);
                if (!findParent(a, b)) {
                    root = unionParent(a, b);
                }

                System.out.println(cnt[root]);
            }
        }
    }

    private static int unionParent(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a > b) {
            parent[a] = b;
            cnt[b] += cnt[a];
            return b;
        } else {
            parent[b] = a;
            cnt[a] += cnt[b];
            return a;
        }
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
