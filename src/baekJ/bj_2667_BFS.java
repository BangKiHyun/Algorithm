package baekJ;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class bj_2667_BFS {
    private static int n;
    private static int[][] map;
    private static Queue<House> houses = new LinkedList<>();
    private static boolean visit[][];
    private static int[] X = {-1, 1, 0, 0};
    private static int[] Y = {0, 0, -1, 1};
    private static PriorityQueue<Integer> houses_list = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visit = new boolean[n][n];
        map = new int[n][n];
        String ex[][] = new String[n][n];

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            ex[i] = s.split("");
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(ex[i][j]);
            }
        }
        solution();
    }

    private static void solution() {
        int dange_cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    houses.add(new House(i, j));
                    visit[i][j] = true;
                    bfs();
                    dange_cnt++;
                }
            }
        }

        System.out.println(dange_cnt);
        while (!houses_list.isEmpty()) {
            System.out.println(houses_list.poll());
        }

    }

    private static void bfs() {
        int house_size = 1;
        while (!houses.isEmpty()) {
            House tmpHouse = houses.poll();
            for (int i = 0; i < 4; i++) {
                int nx = tmpHouse.x + X[i];
                int ny = tmpHouse.y + Y[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visit[nx][ny] && map[nx][ny] == 1) {
                    houses.add(new House(nx, ny));
                    visit[nx][ny] = true;
                    house_size++;
                }
            }
        }
        houses_list.add(house_size);
    }

    private static class House {
        int x, y;

        House(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
