package baekJ;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class bj_1655_PriorityQueue {
    static Queue<Integer> small_num = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2) return -1;
            else return 1;
        }
    });
    static Queue<Integer> big_num = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int mid_num = sc.nextInt();
        int next_num;
        int ans[] = new int[n];

        ans[0] = mid_num;
        small_num.add(mid_num);

        for (int i = 1; i < n; i++) {
            next_num = sc.nextInt();

            if (big_num.size() < small_num.size()) {
                big_num.add(next_num);
            } else {
                small_num.add(next_num);
            }

            if (small_num.peek() >= big_num.peek()) {
                int a = small_num.poll();
                int b = big_num.poll();
                small_num.add(b);
                big_num.add(a);
            }
            ans[i] = small_num.peek();
        }

        for (int i : ans) {
            System.out.println(i);
        }
    }
}
