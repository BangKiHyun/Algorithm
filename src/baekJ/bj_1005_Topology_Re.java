package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_1005_Topology_Re {
    private static int n, k;
    private static int[] inDegree;
    private static int[] time;
    private static int winningPos;
    private static List<Integer>[] connectedList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            inDegree = new int[n + 1];
            time = new int[n + 1];
            connectedList = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                connectedList[i] = new ArrayList<>();
            }

            int idx = 1;
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreElements()) {
                time[idx++] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                connectedList[from].add(to);
                inDegree[to]++;
            }

            winningPos = Integer.parseInt(br.readLine());

            System.out.println(getAnswer());
        }
    }

    private static int getAnswer() {
        int[] accumulationTime = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                accumulationTime[i] = time[i];
            }
        }

        while (!q.isEmpty()) {
            int curPos = q.poll();

            for (int nextPos : connectedList[curPos]) {
                accumulationTime[nextPos] = Math.max(accumulationTime[nextPos], accumulationTime[curPos] + time[nextPos]);
                if (--inDegree[nextPos] == 0) {
                    q.offer(nextPos);
                }
            }
        }

        return accumulationTime[winningPos];
    }
}
