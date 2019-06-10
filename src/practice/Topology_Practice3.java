package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//백준 1948
class Edge {
    int node;
    int time;

    Edge(int node, int time) {
        this.node = node;
        this.time = time;
    }
}

public class Topology_Practice3 {
    static final int MAX = 10001;
    static int n, start, finish;
    static int inDegree[] = new int[MAX];
    static int result[] = new int[MAX];
    static int c[] = new int[MAX];
    static ArrayList<Edge> a[] = new ArrayList[MAX];
    static ArrayList<Edge> b[] = new ArrayList[MAX];

    static void topologySort() {
        Queue q = new LinkedList();
        q.offer(start);

        while (!q.isEmpty()) {
            int x = (int) q.poll();
            for (Edge i : a[x]) {
                if (result[i.node] <= i.time + result[x]) {
                    result[i.node] = i.time + result[x];
                }
                if (--inDegree[i.node] == 0) {
                    q.offer(i.node);
                }
            }
        }
        int cnt = 0;
        q.offer(finish);
        while (!q.isEmpty()) {
            int y = (int) q.poll();
            for (Edge x : b[y]) {
                if (result[y] - result[x.node] == x.time) {
                    cnt++;
                    if (c[x.node] == 0) {
                        q.offer(x.node);
                        c[x.node] = 1;
                    }
                }
            }
        }
        System.out.println(result[finish] + " " + cnt);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        n = sc.nextInt();
        for(int i=1; i<=m; i++){
            a[i] = new ArrayList<>();
            b[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int x, node, time;
            x = sc.nextInt();
            node = sc.nextInt();
            time = sc.nextInt();

            a[x].add(new Edge(node, time));
            b[node].add(new Edge(x, time));
            inDegree[node]++;
        }
        start = sc.nextInt();
        finish = sc.nextInt();
        topologySort();
    }
}
