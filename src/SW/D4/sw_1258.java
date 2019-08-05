package SW.D4;

import java.util.*;

public class sw_1258 {
    static int map[][];
    static ArrayList<Node> arr;
    static int a[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            map = new int[n + 1][n + 1];
            arr = new ArrayList<>();
            a = new int[n];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] != 0) {
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                int x = i;
                for (int j = 0; j < n; j++) {
                    int y = j;
                    if (map[i][j] != 0) {
                        while (true) {
                            y++;
                            if (map[i][y] == 0)
                                break;
                        }
                        while (true) {
                            x++;
                            if (map[x][j] == 0)
                                break;
                        }
                        for (int k = i; k < x; k++) {
                            for (int l = j; l < y; l++) {
                                map[k][l] = 0;
                            }
                        }
                        arr.add(new Node(x, y, x * y));
                    }
                    j = y;
                }
            }
        }
    }

    static class Node {
        int x, y, mul;

        public Node(int x, int y, int mul) {
            this.x = x;
            this.y = y;
            this.mul = mul;
        }
    }
}
