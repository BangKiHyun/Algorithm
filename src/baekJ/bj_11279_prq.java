package baekJ;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class bj_11279_prq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> prq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            int input = sc.nextInt();
            if (input == 0) {
                if (!prq.isEmpty()) {
                    System.out.println(prq.poll());
                } else {
                    System.out.println(0);
                }
            } else prq.add(input);
        }
    }
}
