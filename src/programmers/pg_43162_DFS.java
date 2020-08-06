package programmers;

import java.util.ArrayList;
import java.util.List;

public class pg_43162_DFS {
    private static final int CONNECT = 1;

    public static void main(String[] args) {
        int[][] computers = {{1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}};

        System.out.println(solution(3, computers));
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        List<Integer>[] connectList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            connectList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == CONNECT) {
                    connectList[i].add(j);
                }
            }
        }

        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                checkVisit(i, connectList, visit);
                answer++;
            }
        }

        return answer;
    }

    private static void checkVisit(final int start, final List<Integer>[] connectList, final boolean[] visit) {
        if (visit[start]) {
            return;
        }

        visit[start] = true;
        for (int i : connectList[start]) {
            checkVisit(i, connectList, visit);
        }
    }
}
