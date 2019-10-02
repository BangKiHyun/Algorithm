package baekJ;

import java.util.Scanner;

public class bj_1976_UnionFInd {
    public static int parent[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int relation[] = new int[m + n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                relation[j] = sc.nextInt();
                if (i == j) continue;
                if (relation[j] == 1) {
                    union(i, j);
                }
            }
        }

        for (int i = 1; i <= m; i++) {
            relation[i] = sc.nextInt();
        }
        int check = getParent(relation[1]);
        boolean flag = true;

        for (int i = 2; i <= m; i++) {
            if (check != getParent(relation[i])) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int getParent(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = getParent(parent[x]);
    }

    private static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a == b) return;

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = b;
        }
    }
}
