package baekJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//강호는 N개의 도시로 이루어진 나라에 살고 있다. 각 도시는 M개의 도로로 연결되어 있으며, 각 도로를 지날 때 필요한 시간이 존재한다.
//도로는 잘 연결되어 있기 때문에, 도시 A에서 B로 이동할 수 없는 경우는 존재하지 않는다.
//
//도시 A에서 도시 B로 바로 갈 수 있는 도로가 있거나, 다른 도시를 거쳐서 갈 수 있을 때, 도시 A에서 B를 갈 수 있다고 한다.
//
//강호는 모든 쌍의 도시에 대해서 최소 이동 시간을 구해놓았다. 민호는 이 표를 보고 원래 도로가 몇 개 있는지를 구해보려고 한다.
//모든 쌍의 도시 사이의 최소 이동 시간이 주어졌을 때,
//이 나라에 존재할 수 있는 도로의 개수의 최솟값과 그 때, 모든 도로의 시간의 합을 구하는 프로그램을 작성하시오.
public class bj_1507_플로이드 {
    private static int n;
    private static int[][] distance;
    private static int[][] copy;
    private static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        distance = new int[n][n];
        copy = new int[n][n];
        visit = new boolean[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int dist = Integer.parseInt(st.nextToken());
                distance[i][j] = dist;
                copy[i][j] = dist;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == k || j == k) continue;
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        System.out.println(-1);
                        return;
                    } else if (distance[i][j] == distance[i][k] + distance[k][j]) {
                        copy[i][j] = 0;
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && copy[i][j] != 0) {
                    sum += copy[i][j];
                    visit[i][j] = true;
                    visit[j][i] = true;
                }
            }
        }

        System.out.println(sum);
    }
}
