package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class pg_1832_kakao {

    static int MOD = 20170805;
    static int[] X = {1, 0};
    static int[] Y = {0, 1};
    static int cnt = 0;
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) {
        //int map[][] = {{0, 0, 0,}, {0, 0, 0,}, {0, 0, 0,}};
        int map[][] = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        int ans = solution(3, 6, map);
        System.out.println(ans);
    }

    static public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        q.add(new Node(0, 0));
        bfs(m, n, cityMap);
        answer = cnt;
        return answer;
    }

    static void bfs(int m, int n, int[][] map) {
        while (!q.isEmpty()) {
            Node nd = q.poll();
            int x = nd.x;
            int y = nd.y;

            System.out.println(x + " " + y);

            if (x == m - 1 && y == n - 1) cnt++;

            for (int i = 0; i < 2; i++) {
                int nx = x + X[i];
                int ny = y + Y[i];

                if (nx < m && ny < n  && map[nx][ny] != 1) {
                    if (map[nx][ny] == 0) {
                        q.add(new Node(nx, ny));
                    } else if (map[nx][ny] == 2) {
                        if (x == nx && y != ny) {
                            while (true) {
                                if (ny + 1 < n) {
                                    ny++;
                                } else break;
                                if (map[x][ny] != 1) break;
                            }
                            q.add(new Node(nx, ny));
                        } else if (y == ny && nx != x) {
                            while (true) {
                                if(nx +1 < m){
                                    nx++;
                                }else break;
                                if (map[nx][y] != 1) break;
                            }
                            q.add(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
