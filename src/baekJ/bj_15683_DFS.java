package baekJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class bj_15683_DFS {
    private static int[][] map;
    private static int r, c, min = Integer.MAX_VALUE;
    private static ArrayList<CCTY> list = new ArrayList<CCTY>();

    public static void main(String[] args) {
        init();
        solution(0, map);
        System.out.println(min);
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] > 0 && map[i][j] < 6) {
                    list.add(new CCTY(i, j, map[i][j]));
                }
            }
        }
    }

    private static void solution(int cctvIdx, int copy[][]) {
        int temp[][] = new int[r][c];
        if (cctvIdx == list.size()) {
            min = Math.min(min, getArea(copy));
        } else {
            search(list.get(cctvIdx), cctvIdx, copy, temp);
        }
    }

    private static int getArea(int[][] copy) {
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (copy[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void search(CCTY ccty, int cctyIdx, int[][] copy, int[][] temp) {
        switch (ccty.num) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    copyMap(temp, copy);
                    detect(i, ccty.r, ccty.c, temp);
                    solution(cctyIdx + 1, temp);
                }
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    copyMap(temp, copy);
                    detect(i, ccty.r, ccty.c, temp);
                    detect(i + 2, ccty.r, ccty.c, temp);
                    solution(cctyIdx + 1, temp);
                }
                break;
            case 3:
                for (int i = 0; i < 4; i++) {
                    copyMap(temp, copy);
                    detect(i, ccty.r, ccty.c, temp);
                    detect((i + 1) % 4, ccty.r, ccty.c, temp);
                    solution(cctyIdx + 1, temp);
                }
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    copyMap(temp, copy);
                    detect(i, ccty.r, ccty.c, temp);
                    detect((i + 1) % 4, ccty.r, ccty.c, temp);
                    detect((i + 2) % 4, ccty.r, ccty.c, temp);
                    solution(cctyIdx + 1, temp);
                }
                break;
            case 5:
                copyMap(temp, copy);
                detect(0, ccty.r, ccty.c, temp);
                detect(1, ccty.r, ccty.c, temp);
                detect(2, ccty.r, ccty.c, temp);
                detect(3, ccty.r, ccty.c, temp);
                solution(cctyIdx + 1, temp);
                break;
        }
    }

    private static void copyMap(int[][] temp, int[][] copy) {
        for (int i = 0; i < r; i++) {
            temp[i] = Arrays.copyOf(copy[i], c);
        }
    }

    private static void detect(int dir, int now_r, int now_c, int[][] copy) {
        switch (dir) {
            case 0:
                for (int j = now_c; j < c; j++) {
                    if (copy[now_r][j] == 6) {
                        break;
                    }
                    map[now_r][j] = 7;
                }
                break;
            case 1:
                for (int i = now_r; i < r; i++) {
                    if (copy[i][now_c] == 6) {
                        break;
                    }
                    map[i][now_c] = 7;
                }
                break;
            case 2:
                for (int i = now_c; i >= 0; i--) {
                    if (copy[now_r][i] == 6) {
                        break;
                    }
                    map[now_r][i] = 7;
                }
                break;
            case 3:
                for (int j = now_r; j >= 0; j--) {
                    if (copy[j][now_c] == 6) {
                        break;
                    }
                    map[j][now_c] = 7;
                }
                break;
        }
    }

    private static class CCTY {
        int r, c, num;

        CCTY(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
}
