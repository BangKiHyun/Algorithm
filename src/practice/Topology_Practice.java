package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//백준 2252
public class Topology_Practice {
    static final int MAX = 32000;
    static int n;
    static int inDegree[] = new int[MAX];
    static int result[] = new int[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static void Topology1() {
        Queue q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        for (int i = 1; i <= n; i++) {
            int x = (int) q.peek();
            result[i] = x;
            if (!q.isEmpty()) {
                q.poll();
                for (int j : a[x]) {
                    if (--inDegree[j] == 0) {
                        q.offer(j);
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            a[x].add(y);
            inDegree[y]++;
        }
        Topology1();
    }
}
