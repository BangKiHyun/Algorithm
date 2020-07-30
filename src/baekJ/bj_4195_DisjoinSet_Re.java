package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class bj_4195_DisjoinSet_Re {
    private static int[] parent;
    private static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            parent = new int[n * 2];
            cnt = new int[n * 2];
            for (int i = 0; i < n * 2; i++) {
                parent[i] = i;
                cnt[i] = 1;
            }

            int idx = 0;
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String[] inputLine = br.readLine().split(" ");

                if (!map.containsKey(inputLine[0])) {
                    map.put(inputLine[0], idx++);
                }
                if (!map.containsKey(inputLine[1])) {
                    map.put(inputLine[1], idx++);
                }

                int a = getParent(map.get(inputLine[0]));
                int b = getParent(map.get(inputLine[1]));

                int root = a;
                if (a != b) {
                    root = unionFind(a, b);
                }

                System.out.println(cnt[root]);
            }
        }
    }

    private static int getParent(final int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    private static int unionFind(final int a, final int b) {
        if (a < b) {
            parent[b] = a;
            cnt[a] += cnt[b];
            return a;
        } else {
            parent[a] = b;
            cnt[b] += cnt[a];
            return b;
        }
    }
}
