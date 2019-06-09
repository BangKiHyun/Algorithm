package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.Math.max;

//백준 1516
public class Topology_Practice2 {
    static final int MAX = 501;
    static int n;
    static int inDegree[] = new int[MAX];
    static int result[] = new int[MAX];
    static int time[] = new int[MAX];
    static ArrayList<Integer> a[] = new ArrayList[MAX];

    static void Topology() {
        Queue q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                result[i] = time[i];
                q.offer(i);
            }
        }
        for (int i = 1; i <= n; i++) {
            int x = (int) q.peek();
            q.poll();
            for (int j : a[x]) {
                result[j] = max(result[j], result[x] + time[j]);
                if (--inDegree[j] == 0) {
                    q.offer(j);
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
        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            int t = sc.nextInt();
            time[i] = t;
            while (true) {
                int x = sc.nextInt();
                if (x == -1) {
                    break;
                }
                inDegree[i]++;
                a[x].add(i);
            }
        }
        Topology();
    }
}
