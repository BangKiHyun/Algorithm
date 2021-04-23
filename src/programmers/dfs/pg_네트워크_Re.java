package programmers.dfs;

import java.util.ArrayList;
import java.util.List;

public class pg_네트워크_Re {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}};

        System.out.println(solution(n, computers));
    }

    public static int solution(int n, int[][] computers) {
        boolean[] visit = new boolean[n];
        List<Integer>[] connectedList = new ArrayList[n];
        for (int idx = 0; idx < n; idx++) {
            connectedList[idx] = new ArrayList();
        }

        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                if (from == to) continue;
                if (computers[from][to] != 1) continue;
                connectedList[from].add(to);
                connectedList[to].add(from);
            }
        }

        int countOfNetwork = 0;
        for (int idx = 0; idx < n; idx++) {
            if (!visit[idx]) {
                visit[idx] = true;
                countOfNetwork++;
                checkConnectedNetwork(idx, connectedList, visit);
            }
        }
        return countOfNetwork;
    }

    private static void checkConnectedNetwork(int curNetwork, List<Integer>[] connectedList, boolean[] visit) {
        for (int nextNetwork : connectedList[curNetwork]) {
            if (!visit[nextNetwork]) {
                visit[nextNetwork] = true;
                checkConnectedNetwork(nextNetwork, connectedList, visit);
            }
        }
    }
}