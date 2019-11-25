package problem;

import java.util.*;

public class bj_17142_BFS {
    private static int n, m;
    private static int[][] map, copy;
    private static List<Virus> virusList = new ArrayList<>();
    private static int min = Integer.MAX_VALUE;
    private static boolean checkVirus[];
    private static int virusSize;
    private static LinkedList<Virus> activateVirus = new LinkedList<>();
    private static int dx[] = {-1, 1, 0, 0};
    private static int dy[] = {0, 0, -1, 1};
    private static int safeArea = 0;
    private static boolean visit[][];

    public static void main(String[] args) {
        init();
        permutate(0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];
        copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j, 0));
                } else if (map[i][j] == 0) {
                    safeArea++;
                }
            }
        }

        virusSize = virusList.size();
        checkVirus = new boolean[virusSize];
    }

    private static void permutate(int start) {
        if (activateVirus.size() == m) {
            copyMap();
            visit = new boolean[n][n];
            int spread[] = spreadVirus(activateVirus);
            if (isValid(spread[1])) {
                min = Math.min(min, spread[0]);
            }
            return;
        }

        for (int i = start; i < virusSize; i++) {
            if (!checkVirus[i]) {
                checkVirus[i] = true;
                activateVirus.add(virusList.get(i));
                permutate(i + 1);
                checkVirus[i] = false;
                activateVirus.pollLast();
            }
        }
    }

    private static void copyMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = map[i][j];
            }
        }
    }

    private static int[] spreadVirus(Queue<Virus> q) {
        int max = 0;
        int spreadCnt = 0;

        while (!q.isEmpty()) {
            Virus now = q.poll();
            visit[now.x][now.y] = true;
            max = Math.max(max, now.time);

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny] && copy[nx][ny] != 1) {
                    visit[nx][ny] = true;
                    if (copy[nx][ny] == 2) {
                        q.add(new Virus(nx, ny, now.time));
                    } else {
                        q.add(new Virus(nx, ny, now.time + 1));
                        spreadCnt++;
                    }
                }
            }
        }

        return new int[]{max, spreadCnt};
    }

    private static boolean isValid(int cnt) {
        if (cnt != safeArea) return false;
        return true;
    }

    private static class Virus {
        int x, y, time;

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
