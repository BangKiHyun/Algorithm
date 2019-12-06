package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj_11724_DFS {
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2)
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] lists = new ArrayList[v + 1];
        for (int i = 1; i <= u; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            //M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            lists[first].add(second);
            lists[second].add(first);
        }

        check = new boolean[u + 1];
        int cnt = 0;

        for (int i = 1; i <= u; i++) {
            if (!check[i]) {
                cnt++;
                check[i] = true;
                goDFS(i, lists);
            }
        }
        System.out.println(cnt);
    }

    private static void goDFS(int num, ArrayList<Integer>[] lists) {
        for (int j : lists[num]) {
            if (!check[j]) {
                check[j] = true;
                goDFS(j, lists);
            }
        }
    }
}
