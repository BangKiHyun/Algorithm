package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_9938_DisjoinSet {
    private static int[] parent;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputLine = br.readLine().split(" ");
        int n = Integer.parseInt(inputLine[0]);
        int m = Integer.parseInt(inputLine[1]);

        parent = new int[m + 1];
        visit = new boolean[m + 1];
        for (int i = 1; i <= m; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            inputLine = br.readLine().split(" ");
            int a = Integer.parseInt(inputLine[0]);
            int b = Integer.parseInt(inputLine[1]);

            if (!visit[a]) {
                visit[a] = true;
                union(a, b);
            } else if (!visit[b]) {
                visit[b] = true;
                union(b, a);
            } else if (!visit[getParent(parent[a])]) {
                visit[getParent(parent[a])] = true;
                union(a, b);
            } else if (!visit[getParent(parent[b])]) {
                visit[getParent(parent[b])] = true;
                union(b, a);
            } else {
                System.out.println("SMECE");
            }
        }
    }

    private static int getParent(final int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }

    private static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        parent[a] = b;

        System.out.println("LADICA");
    }
}
