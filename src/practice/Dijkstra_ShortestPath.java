package practice;

public class Dijkstra_ShortestPath {
    static int num = 6;
    static int INF = 1000000000;

    //전체 그래프 초기화
    static int a[][] = {
            {0, 2, 5, 1, INF, INF},
            {2, 0, 3, 2, INF, INF},
            {5, 3, 0, 3, 1, 5},
            {1, 2, 3, 0, 1, INF},
            {INF, INF, 1, 1, 0, 2},
            {INF, INF, 5, INF, 2, 0},
    };

    static boolean v[] = new boolean[6]; // 방문한 노드
    static int d[] = new int[6]; // 최단 거리

    //가장 최소 거리를 가지는 정점 반환
    static int getSmallIndex() {
        int min = INF;
        int idx = 0;
        for (int i = 0; i < num; i++) {
            if (d[i] < min && !v[i]) {
                min = d[i];
                idx = i;
            }
        }
        return idx;
    }

    static void dijkstra(int start) {
        for (int i = 0; i < num; i++) {
            d[i] = a[start][i];
        }
        v[start] = true;
        for (int i = 0; i < num - 2; i++) {
            int current = getSmallIndex();
            v[current] = true;
            for (int j = 0; j < 6; j++) {
                if (!v[j]) {
                    if (d[current] + a[current][j] < d[j]) {
                        d[j] = d[current] + a[current][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        dijkstra(0);
        for (int i = 0; i < num; i++) {
            System.out.print(d[i] + " ");
        }
    }
}
