package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2623_Topology {
    private static int n;
    private static int[] inDegree;
    private static ArrayList<Integer>[] lists;
    private static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        inDegree = new int[n + 1];
        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            for (int j = 1; j < cnt; j++) {
                int second = Integer.parseInt(st.nextToken());
                lists[first].add(second);
                inDegree[second]++;
                first = second;
            }
        }

        goTopology();
        if(hasCycle()){
            System.out.println(0);
            return;
        }

        for (int answer : ans) {
            System.out.println(answer);
        }
    }

    private static void goTopology() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                ans.add(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : lists[cur]) {
                if (--inDegree[next] == 0) {
                    q.offer(next);
                    ans.add(next);
                }
            }
        }
    }

    private static boolean hasCycle() {
        return ans.size() != n;
    }
}
