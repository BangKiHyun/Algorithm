package baekJ;

import java.util.*;

public class bj_1966_Queue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int textList[];

        for (int test_case = 0; test_case < T; test_case++) {
            int n = sc.nextInt();
            int position = sc.nextInt();
            textList = new int[n];

            for (int i = 0; i < n; i++) {
                textList[i] = sc.nextInt();
            }

            int ans = findOrder(position, textList);
            System.out.println(ans);
        }
    }

    private static int findOrder(int position, int[] list) {
        int ans = 1;
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i : list) {
            q.add(i);
        }

        while (!q.isEmpty()) {
            for (int i = 0; i < list.length; i++) {
                if (list[i] == q.peek()) {
                    if (i == position) return ans;
                    q.poll();
                    ans++;
                }
            }
        }
        return -1;
    }
}
