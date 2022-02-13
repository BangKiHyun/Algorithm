package programmers.kakao.dfs;

import java.util.ArrayList;
import java.util.List;

public class pg_92343_양과늑대 {

    private static final int SHEEP = 0;
    private static final int WOLF = 1;
    private static final int TEMP = -1;

    private static int N;
    private static int max = 0;
    private static boolean[][][] visit;
    private static List<Integer>[] nodeList;

    public static void main(String[] args) {
        final pg_92343_양과늑대 task = new pg_92343_양과늑대();
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(task.solution(info, edges));
    }

    public int solution(int[] info, int[][] edges) {
        N = info.length + 1;
        visit = new boolean[N][N][N];
        nodeList = new ArrayList[N];
        for (int idx = 0; idx < N; idx++) {
            nodeList[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < edges.length; idx++) {
            int from = edges[idx][0];
            int to = edges[idx][1];
            nodeList[from].add(to);
            nodeList[to].add(from);
        }

        visit[0][0][0] = true;
        goDFS(0, 0, 0, info);
        return max;
    }

    private void goDFS(int idx, int sheep, int wolf, int[] info) {
        int preVal = TEMP;

        if (info[idx] != TEMP) {
            if (info[idx] == SHEEP) {
                preVal = SHEEP;
                sheep++;
            } else {
                preVal = WOLF;
                wolf++;
            }
        }

        if (sheep <= wolf) return;
        max = Math.max(max, sheep);

        for (int next : nodeList[idx]) {
            if (!visit[next][sheep][wolf]) {
                visit[next][sheep][wolf] = true;
                info[idx] = TEMP;
                goDFS(next, sheep, wolf, info);
                info[idx] = preVal;
                visit[next][sheep][wolf] = false;

            }
        }
    }
}
