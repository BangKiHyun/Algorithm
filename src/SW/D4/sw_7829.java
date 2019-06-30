package SW.D4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class sw_7829 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P, min, max, result, num = 0;
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++) {
            P = sc.nextInt();
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < P; i++) {
                num = sc.nextInt();
                a.add(num);
            }
            if (P == 1) result = num * num;
            else {
                Collections.sort(a);
                min = a.get(0);
                max = a.get(P - 1);
                result = min * max;
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}
