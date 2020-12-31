package programmers.dfs;

import java.util.ArrayList;
import java.util.List;

public class pg_네트워크 {

    public static void main(String[] args) {
        int n = 3;
        int[][] computers = {{1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}};

        System.out.println(solution(n, computers));
    }

    public static int solution(int n, int[][] computers) {
        List<Integer>[] connectedList = new ArrayList[n];
        initListArray(n, connectedList);

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    connectedList[i].add(j);
                    connectedList[j].add(i);
                }
            }
        }

        int ans = 0;
        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                ans++;
                goDFS(i, connectedList, visit);
            }
        }
        return ans;
    }

    private static void goDFS(int idx, List<Integer>[] connectedList, boolean[] visit) {
        for (int connectedNumber : connectedList[idx]) {
            if (visit[connectedNumber]) continue;
            visit[connectedNumber] = true;
            goDFS(connectedNumber, connectedList, visit);
        }
    }

    private static void initListArray(int n, List<Integer>[] connectedList) {
        for (int i = 0; i < n; i++) {
            connectedList[i] = new ArrayList<>();
        }
    }
}
