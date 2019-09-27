package baekJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_1516_Topology {
    private static ArrayList<Integer>[] build_list;
    private static int inDegree[];
    private static int build_time[];
    private static int tmp_time[];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        build_list = new ArrayList[n + 1];
        inDegree = new int[n + 1];
        build_time = new int[n + 1];
        tmp_time = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            build_list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            int time = sc.nextInt();
            build_time[i] = time;
            tmp_time[i] = time;

            while (true) {
                int num = sc.nextInt();
                if (num == -1) break;
                build_list[num].add(i);
                inDegree[i]++;
            }
        }
        Topology(n);
        output();
    }

    private static void output() {
        for (int i = 1; i < build_time.length; i++) {
            System.out.println(build_time[i]);
        }
    }

    private static void Topology(int n) {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i : build_list[x]) {
                build_time[i] = Math.max(build_time[i], build_time[x] + tmp_time[i]);
                if (--inDegree[i] == 0) {
                    q.offer(i);
                }
            }
        }
    }
}
