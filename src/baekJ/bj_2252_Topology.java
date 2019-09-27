package baekJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj_2252_Topology {
    private static ArrayList<Integer>[] student_list;
    private static int inDegree[];
    private static int answer[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        student_list = new ArrayList[n + 1];
        inDegree = new int[n + 1];
        answer = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            student_list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int student1 = sc.nextInt();
            int student2 = sc.nextInt();
            student_list[student1].add(student2);
            inDegree[student2]++;
        }

        Topology(n);
        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    private static void Topology(int n) {
        Queue<Integer> q = new LinkedList<>();
        int idx = 1;

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                answer[idx] = i;
                idx++;
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i : student_list[x]) {
                if (--inDegree[i] == 0) {
                    q.offer(i);
                    answer[idx] = i;
                    idx++;
                }
            }
        }
    }
}
