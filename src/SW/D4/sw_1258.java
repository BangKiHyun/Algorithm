package SW.D4;

import java.util.*;

public class sw_1258 {
    static int map[][];
    static ArrayList<Node> arr;
    static int a[];
    static int ans[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            map = new int[n + 1][n + 1];
            arr = new ArrayList<>();
            a = new int[n+1];
            ans = new int[n * 2];
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
                    int row = 0;
                    int col = 0;
                    if (map[i][j] != 0) {
                        while (true) {
                            y++;
                            row++;
                            if (map[i][y] == 0)
                                break;
                        }
                        while (true) {
                            x++;
                            col++;
                            if (map[x][j] == 0)
                                break;
                        }
                        for (int k = i; k < x; k++) {
                            for (int l = j; l < y; l++) {
                                map[k][l] = 0;
                            }
                        }
                        arr.add(new Node(row, col, row * col));
                    }
                    x = i;
                    j = y;
                }
            }
            Collections.sort(arr);
            System.out.print("#" + test_case + " " + arr.size());
            for (Node s : arr) {
                System.out.print(" " + s.y + " " + s.x);
            }
            System.out.println();
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, mul;

        public Node(int x, int y, int mul) {
            this.x = x;
            this.y = y;
            this.mul = mul;
        }

        @Override
        public int compareTo(Node n) {
            if (this.mul < n.mul) {
                return -1;
            } else if (this.mul > n.mul) {
                return 1;
            } else if (this.mul == n.mul) {
                if (this.x < n.x) {
                    return -1;
                } else
                    return 1;
            }
            return 0;
        }
    }
}
