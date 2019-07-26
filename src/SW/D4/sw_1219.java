package SW.D4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_1219 {
    static int MAX = 100;
    static ArrayList<Integer> a[] = new ArrayList[MAX];
    static int inDegree[];
    static int len;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int test_case = 1; test_case <= 10; test_case++) {
            int T = sc.nextInt();
            len = sc.nextInt();
            for (int i = 0; i < MAX; i++) {
                a[i] = new ArrayList<>();
            }
            inDegree = new int[MAX];
            for (int i = 0; i < len; i++) {
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                a[n1].add(n2);
                inDegree[n2]++;
            }
            int ans = Topology();
            System.out.println("#" + test_case + " " + ans);
        }
    }

    public static int Topology() {
        int ans = 0;
        if (len == 0) return ans;

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int j : a[x]) {
                if (j == 99) {
                    ans = 1;
                    return ans;
                }
                --inDegree[j];
                if (inDegree[j] > -1) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}
